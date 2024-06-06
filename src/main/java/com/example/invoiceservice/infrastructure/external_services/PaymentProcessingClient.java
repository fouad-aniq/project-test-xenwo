package com.example.invoiceservice.infrastructure.external_services;

import com.example.invoiceservice.application.dtos.InvoiceDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import java.util.concurrent.CompletableFuture;

@Service
public class PaymentProcessingClient implements com.example.invoiceservice.domain.ports.PaymentProcessingPort {
    private static final Logger logger = LoggerFactory.getLogger(PaymentProcessingClient.class);
    private final RestTemplate restTemplate;
    @Value("${payment.service.url}")
    private String paymentServiceConfiguredUrl;

    @Autowired
    public PaymentProcessingClient(Restemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public boolean setupPayment(InvoiceDTO invoiceDTO) {
        try {
            restTemplate.postForObject(paymentServiceConfiguredUrl + "/setup", invoiceDTO, String.class);
            return true;
        } catch (HttpClientErrorException | ResourceAccessException e) {
            logger.error("Network or HTTP Client Error during payment setup: ", e);
            return false;
        } catch (Exception e) {
            logger.error("Unexpected error during payment setup: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public CompletableFuture<Boolean> setupPaymentAsync(InvoiceDTO invoiceDTO) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                restTemplate.postForObject(paymentServiceConfiguredUrl + "/setup-async", invoiceDTO, String.class);
                return true;
            } catch (Exception e) {
                logger.error("Error setting up payment asynchronously: " + e.getMessage(), e);
                return false;
            }
        });
    }

    @Override
    public void handlePaymentValidation(String validationMessage) {
        logger.info("Payment validation: " + validationMessage);
    }

    @Override
    public void handlePaymentFailure(String errorMessage) {
        logger.error("Payment failure: " + errorMessage);
    }

    @Override
    public boolean validatePaymentSetup(InvoiceDTO invoiceDTO) {
        // Assuming some validation logic here
        return true;
    }
}