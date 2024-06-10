package com.example.payment.application.use_cases;

import com.example.payment.application.dtos.PaymentDTO;
import com.example.payment.domain.services.PaymentProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;

@Component
public class ProcessPaymentUseCase {

    @Getter @Setter
    private final PaymentProcessingService paymentProcessingService;

    @Autowired
    public ProcessPaymentUseCase(PaymentProcessingService paymentProcessingService) {
        this.paymentProcessingService = paymentProcessingService;
    }

    public PaymentDTO processPayment(PaymentDTO paymentDTO) throws Exception {
        if (paymentDTO == null || !paymentDTO.isValid()) {
            throw new IllegalArgumentException("Invalid Payment data");
        }
        try {
            return paymentProcessingService.processPayment(paymentDTO);
        } catch (Exception e) {
            // Log and handle the exception appropriately
            throw new RuntimeException("Error processing payment", e);
        }
    }
}