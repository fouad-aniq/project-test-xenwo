package com.example.payment.application.services;

import com.example.payment.domain.services.PaymentProcessing+Service;
import com.example.payment.infrastructure.configuration.PaymentConfigManager;
import com.example.payment.application.dtos.PaymentDTO;
import com.example.payment.domain.entities.PaymentResult;
import com.example.payment.domain.exceptions.PaymentValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;

@Data
@Service
@Aspect
public class PaymentApplicationService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentApplicationService.class);
    @Autowired
    private PaymentProcessingService paymentProcessingService;
    @Autowired
    private PaymentConfigManager paymentConfigManager;

    @Before("execution(* com.example.payment.application.services.PaymentApplicationService.processPayment(..))")
    public void logBeforeProcessPayment() {
        logger.info("Starting payment processing");
    }

    @AfterReturning(pointcut = "execution(* com.example.payment.application.services.PaymentApplicationService.processPayment(..))", returning="result")
    public void logAfterProcessPayment(PaymentDTO result) {
        logger.info("Payment processed successfully: {}", result.getTransactionId());
    }

    @AfterThrowing(pointcut = "execution(* com.example.payment.application.services.PaymentApplicationService.processPayment(..))", throwing="ex")
    public void logErrorDuringPayment(Exception ex) {
        logger.error("Error during payment processing: {}", ex.getMessage());
    }

    public PaymentDTO processPayment(PaymentDTO paymentDto) throws PaymentValidationException {
        if (paymentDto == null) {
            throw new PaymentValidationException("Payment data is null");
        }
        paymentDto.validate();
        PaymentResult result = paymentProcessingService.processPayment(paymentDto);
        if (result.getStatus().equals("success")) {
            return new PaymentDTO(result.getTransactionId(), result.getAmount(), result.getCurrency(), paymentDto.getPaymentMethod(), paymentDto.getCardDetails());
        } else {
            throw new PaymentValidationException("Failed to process payment: " + result.getStatus());
        }
    }
}