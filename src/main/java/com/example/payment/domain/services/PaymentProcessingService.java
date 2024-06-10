package com.example.payment.domain.services;

import com.example.payment.application.dtos.PaymentDTO;
import com.example.payment.domain.entities.PaymentResult;
import com.example.payment.domain.exceptions.PaymentExecutionException;
import com.example.payment.domain.exceptions.PaymentInitializationException;
import com.example.payment.domain.ports.PaymentGatewayPort;
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
     * @throws PaymentInitializationException if fails to initialize the payment
     * @throws PaymentExecutionException if fails to execute the payment
     */
    @Transactional
    public PaymentResult processPayment(PaymentDTO paymentDTO) throws PaymentInitializationException, PaymentExecutionException {
        if (paymentDTO == null || paymentDTO.getAmount() <= 0 || paymentDTO.getCurrency().isEmpty() || paymentDTO.getPaymentMethod().isEmpty() || paymentDTO.getCardDetails() == null) {
            throw new IllegalArgumentException("Payment details are incomplete or invalid.");
        }
        logger.debug("Starting payment processing");
        logger.debug("Initializing payment");
        paymentGatewayPort.initializePayment(paymentDTO);
        logger.debug("Executing payment");
        PaymentResponse response = paymentGatewayPort.executePayment(paymentDTO);
        double totalCost = calculateTotalCost(paymentDTO.getAmount(), response.getDiscount(), paymentDTO.getTaxRate());
        logger.debug("Payment processed successfully");
        return new PaymentResult(response.getTransactionId(), "success", "https://example.com/receipts/" + response.getTransactionId() + ".pdf", totalCost);
    }

    /**
     * Calculates total cost after applying discounts and taxes.
     *
     * @param amount initial amount
     * @param discount applied discount
     * @var taxRate applicable tax rate
     * @return total cost
     */
    public double calculateTotalCost(double amount, double discount, double taxRate) {
        if (amount < 0 || discount < 0 || taxRate < 0) throw new IllegalArgumentException("Negative values not allowed");
        double subtotal = amount - discount;
        double taxes = subtotal * taxRate;
        logger.debug("Calculating total cost");
        return subtotal + taxes;
    }
}