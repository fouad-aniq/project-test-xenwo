package com.example.payment.application.services;

import com.example.payment.domain.services.PaymentProcessingService;
import com.example.payment.infrastructure.configuration.PaymentConfigManager;
import com.example.payment.application.dtos.PaymentDTO;
import com.example.payment.domain.entities.PaymentResult;
import com.example.payment.domain.exceptions.PaymentValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
        logger.info("Starting payment processing: {}", this); 
    }

    @AfterReturning(pointcut = "execution(* com.example.payment.application.services.PaymentApplicationService.process(...)", returning="result")
    public void logAfterProcessPayment(PaymentDTO result) {
        logger.info("Payment processed successfully: Transaction ID {}", result.getTransactionId());
    }

    @AfterThrowing(pointcut = "execution(* com.example.payment.application.services.PaymentApplicationService.processPayment(..))", throwing="ex")
    public void logErrorDuringPayment(Exception ex) {
        logger.error("Error during payment processing: {}", ex.getMessage());
    }

    @Transactional
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