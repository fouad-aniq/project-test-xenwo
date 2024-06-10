package com.example.payment.domain.services;

import com.example.payment.application.dtos.PaymentDTO;
import com.example.payment.domain.entities.PaymentResult;
import com.example.payment.domain.ports.PaymentGatewayPort;
import com.example.payment.domain.value_objects.PaymentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PaymentProcessingService {

    private final PaymentGatewayPort paymentGatewayPort;
    private static final Logger logger = LoggerFactory.getLogger(PaymentProcessingService.class);

    public PaymentProcessingService(PaymentGatewayPort paymentGatewayPort) {
        this.paymentGatewayPort = paymentGatewayPort;
    }

    public PaymentResult processPayment(PaymentDTO paymentDTO) {
        try {
            logger.debug("Starting payment initialization.");
            paymentGatewayPort.initializePayment(paymentDTO);
            logger.info("Payment initialized successfully for transactionId: {}", paymentDTO.getTransactionId());
            PaymentResponse response = paymentGatewayPort.executePayment(paymentDTO);
            logger.debug("Payment executed, processing total cost.");
            double totalCost = calculateTotalCost(response.getAmount(), response.getDiscount(), response.getTaxRate());
            logger.info("Payment processed successfully with total cost: {}", totalCost);
            return new PaymentResult(response.getTransactionId(), totalCost, response.getStatus(), response.getReceiptUrl());
        } catch (Exception e) {
            logger.error("Error processing payment for transactionId: {}", paymentDTO.getTransactionId(), e);
            return new PaymentResult(null, 0, "failure", null);
        }
    }

    private double calculateTotalCost(double amount, double discount, double taxRate) {
        double discountedAmount = amount - discount;
        double taxAmount = discountedAmount * taxRate;
        return discountedAmount + taxAmount;
    }
}