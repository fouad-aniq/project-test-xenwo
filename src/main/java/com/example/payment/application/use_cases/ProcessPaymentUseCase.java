package com.example.payment.application.use_cases;

import com.example.payment.application.dtos.PaymentDTO;
import com.example.payment.domain.entities.PaymentResult;
import com.example.payment.domain.services.PaymentProcessingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessPaymentUseCase {

    private static final Logger logger = LoggerFactory.getLogger(ProcessPaymentUseCase.class);
    private final PaymentProcessingService paymentProcessingService;

    @Autowired
    public ProcessPaymentUseCase(PaymentProcessingService paymentProcessingService) {
        this.paymentProcessingService = paymentProcessingService;
    }

    public PaymentResult processPayment(PaymentDTO paymentDTO) {
        if (paymentDTO == null) {
            throw new IllegalArgumentException("PaymentDTO cannot be null");
        }
        validatePaymentDTO(paymentDTO);
        try {
            logger.info("Starting payment processing for transactionId: {}", paymentDTO.getTransactionID());
            PaymentResult result = paymentProcessingService.processPayment(paymentDTO);
            logger.info("Processed payment successfully for transactionID: {}", paymentDTO.getTransactionID());
            return result;
        } catch (Exception e) {
            logger.error("Error during payment processing for transactionID: {}", paymentDTO.getTransactionID(), e);
            throw new RuntimeException("Failed to process payment", e);
        }
    }

    private void validatePaymentDTO(PaymentDTO paymentDTO) {
        if (paymentDTO.getCurrency() == null || !paymentDTO.getCurrency().matches("^[A-Z]{3}$")) {
            throw new IllegalArgumentException("Invalid currency format.");
        }
        if (paymentDTO.getPaymentMethod() == null || paymentDTO.getPaymentMethod().isEmpty()) {
            throw new IllegalArgumentException("Payment method cannot be empty.");
        }
    }
}