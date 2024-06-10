package com.example.payment.adapters.secondary;

import com.example.payment.domain.ports.PaymentGatewayPort;
import com.example.payment.application.dtos.PaymentDTO;
import com.example.payment.domain.value_objects.PaymentResponse;
import com.example.payment.domain.exceptions.PaymentInitializationException;
import com.example.payment.domain.exceptions.PaymentExecutionException;
import com.example.payment.domain.ports.StripeClientPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.stripe.exception.StripeException;

public class StripePaymentAdapter implements PaymentGatewayPort {

    private static final Logger logger = LoggerFactory.getLogger(StripePaymentAdapter.class);
    private StripeClientPort stripeClient;

    public StripePaymentAdapter(StripeClientPort stripeClient) {
        this.stripeClient = stripeClient;
    }

    @Override
    public void initializePayment(PaymentDTO paymentDetails) throws PaymentInitializationException {
        try {
            logger.info("Initializing payment with details: {}", paymentDetails);
            stripeClient.initialize(paymentDetails);
        } catch (StripeException e) {
            logger.error("Stripe initialization failed", e);
            throw new PaymentInitializationException("Failed to initialize payment", e);
        } catch (Exception e) {
            logger.error("Unexpected error during payment initialization", e);
            throw new PaymentInitializationException("Unexpected error", e);
        }
    }

    @Override
    public PaymentResponse executePayment(PaymentDTO paymentDetails) throws PaymentExecutionException {
        try {
            logger.info("Executing payment with details: {}", paymentDetails);
            return stripeClient.executePayment(paymentDetails);
        } catch (StripeException e) {
            logger.error("Stripe payment execution failed", e);
            throw new PaymentExecutionException("Payment execution failed", e);
        } catch (Exception e) {
            logger.error("Unexpected error during payment execution", e);
            throw new PaymentExecutionException("Unexpected error", e);
        }
    }
}