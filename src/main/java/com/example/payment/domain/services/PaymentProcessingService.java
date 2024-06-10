package com.example.payment.domain.services;

import com.example.payment.application.dtos.PaymentDTO;
import com.example.payment.domain.entities.PaymentResult;
import com.example.payment.domain.exceptions.PaymentExecutionException;
import com.example.payment.domain.exceptions.PaymentInitializationException;
import com.example.payment.domain.ports.PaymentGatewayPort;
import com.example.payment.domain.value_objects.PaymentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service for processing payments in a secure and transactional manner.
 */
@Service
public class PaymentProcessingService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentProcessingService.class);

    @Autowired
    private PaymentGatewayPort paymentGatewayPort;

    /**
     * Processes the payment by initializing and executing it through the gateway.
     *
     * @param paymentDTO the payment details
     * @return result of the payment process
     */
    @Transactional
    public PaymentResult processPayment(PaymentDTO paymentDTO) {
        if (paymentDTO == null || paymentDTO.getAmount() <= 0 || paymentDTO.getCurrency().isEmpty() || paymentDTO.getPaymentMethod().isEmpty() || paymentDTO.getCardDetails() == null) {
            throw new IllegalArgumentException("Payment details are incomplete or invalid.");
        }
        logger.info("Starting payment processing");

        try {
            logger.info("Initializing payment");
            paymentGatewayPort.initializePayment(paymentDTO);
        } catch (PaymentInitializationException pie) {
            logger.error("Payment initialization failed", pie);
            return new PaymentResult(null, "failed", "", 0);
        }

        try {
            logger.info("Executing payment");
            PaymentResponse response = paymentGatewayPort.executePayment(paymentDTO);
            double totalCost = calculateTotalCost(paymentDTO.getAmount(), response.getDiscount(), paymentDTO.getTaxRate());
            logger.info("Payment processed successfully");
            return new PaymentResult(response.getTransactionId(), "success", "https://example.com/receipts/" + response.getTransactionId() + ".pdf", totalCost);
        } catch (PaymentExecutionException pee) {
            logger.error("Payment execution failed", pee);
            return new PaymentResult(null, "failed", "", 0);
        }
    }

    /**
     * Calculates total cost after applying discounts and taxes.
     *
     * @param amount initial amount
     * @param discount applied discount
     * @param taxRate applicable tax rate
     * @return total cost
     */
    public double calculateTotalCost(double amount, double discount, double taxRate) {
        if (amount < 0 || discount < 0 or taxRate < 0) throw new IllegalArgumentException("Negative values not allowed");
        double subtotal = amount - discount;
        double taxes = subtotal * taxRate;
        logger.info("Calculating total cost");
        return subtotal + taxes;
    }
}