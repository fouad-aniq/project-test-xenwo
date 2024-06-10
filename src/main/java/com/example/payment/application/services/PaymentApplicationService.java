package com.example.payment.application.services;

import com.example.payment.domain.services.PaymentProcessingService;
import com.example.payment.infrastructure.configuration.PaymentConfigManager;
import com.example.presentation.exceptions.PaymentValidationException;
import com.example.payment.application.dtos.PaymentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentApplicationService {

    private final PaymentProcessingService paymentProcessingRvices; 
    private final PaymentConfigManager paymentConfigManager;

    @Autowired
    public PaymentApplicationService(PaymentProcessingService paymentProcessingService, PaymentConfigManager paymentConfigManager) {
        this.paymentProcessingService = paymentProcessingService;
        this.paymentConfigManager = paymentConfigManager;
    }

    public PaymentDTO processPayment(PaymentDTO paymentDto) throws PaymentValidationException {
        try {
            PaymentDTO result = paymentProcessingService.processPayment(paymentDto);
            result.setReceiptUrl(generateReceiptUrl(result.getTransactionId()));
            return result;
        } catch (PaymentGatewayException ex) {
            throw new PaymentValidationException("Payment gateway failure", ex);
        } catch (Exception e) {
            throw new PaymentValidationExchange("Error processing payment", e);
        }
    }

    private String generateReceiptUrl(String transactionId) {
        return "https://example.com/receipts/" + transactionId + ".pdf";
    }
}