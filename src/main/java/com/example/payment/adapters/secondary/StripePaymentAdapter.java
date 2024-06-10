package com.example.payment.adapters.secondary;

import com.stripe.StripeClient;
import com.stripe.exception.StripeException;
import com.stripe.exception.CardException;
import com.stripe.exception.RateLimitException;
import com.example.payment.domain.ports.PaymentGatewayPort;
import com.example.payment.application.dtos.PaymentDTO;
import com.example.payment.domain.value_objects.PaymentResponse;
import com.example.payment.domain.exceptions.PaymentInitializationException;
import com.example.payment.domain.exceptions.PaymentExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StripePaymentAdapter implements PaymentGatewayPort {

    private static final Logger logger = LoggerFactory.getLogger(StripePaymentAdapter.class);
    private StripeClient stripeClient;

    public StripePaymentAdapter(StripeClient stripeClient) {
        this.stripeClient = stripeClient;
        logger.info("StripePaymentAdapter initialized.");
    }

    @Override
    public void initializePayment(PaymentDTO paymentDetails) throws PaymentInitializationException {
        validatePaymentDetails(paymentDetails);
        try {
            logger.debug("Initializing payment for amount: {}", paymentDetails.getAmount());
            stripeClient.createPaymentIntent(paymentDetails);
            logger.info("Payment initialized successfully.");
        } catch (StripeException e) {
            logger.error("Error initializing payment", e);
            throw new PaymentInitializationException("Failed to initialize payment", e);
        }
    }

    @Override
    public PaymentResponse executePayment(PaymentDTO paymentDetails) throws PaymentExecutionException {
        validatePaymentDetails(paymentDetails);
        try {
            logger.debug("Executing payment.");
            PaymentResponse response = stripeClient.createCharge(paymentDetails);
            logger.info("Payment executed successfully: Transaction ID {}", response.getTransactionId());
            return response;
        } catch (CardException | RateLimitException e) {
            logger.error("Payment execution failed", e);
            rollbackTransaction();
            throw new PaymentExecutionException("Payment execution failed", e);
        }
    }

    private void rollbackTransaction() {
        logger.debug("Rolling back transaction due to failure.");
        // Logic to reverse any partial changes made during the payment process
    }

    private void validatePaymentDetails(PaymentDTO paymentDetails) {
        logger.debug("Validating payment details.");
        if (!isValidPaymentDetails(paymentDetails)) {
            throw new IllegalArgumentException("Invalid payment details");
        }
    }

    private boolean isValidPaymentDetails(PaymentDTO paymentDetails) {
        return paymentDetails != null && paymentDetails.getAmount() > 0 && paymentDetails.getCardNumber() != null && !paymentDetails.getCardNumber().isEmpty(); // Enhanced validation logic
    }
}