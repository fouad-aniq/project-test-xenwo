package com.example.payment.application.services;

import com.example.payment.domain.services.PaymentProcessingService;
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

    private static final Logger logger = LoggerFactory.getLogger(PaymentApplicationS... (truncated for brevity)