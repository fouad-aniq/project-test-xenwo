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
import org.springframework.transaction.annotation.Transactional;
import java.util.concurrent.CompletableFuture;

@Service
public class PaymentProcessingClient implements com.example.invoiceservice.domain.ports.PaymentProcessingPort {
    private static final Logger logger = LoggerFactory.getLogger(PaymentProcessingClient.class);
    private final RestTemplate restTemplate;
    @Value("${payment.service.url}")
    private String paymentServiceUrl;

    @Autowired
    public PaymentProcessingClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    @Transactional
    public boolean setupPayment(InvoiceDTO invoiceDTO) {
        try {
            restTemplate.postForObject(paymentServiceUrl + "/setup", invoiceDTO, String.class);
            return true;
        } catch (HttpClientErrorException | ResourceAccessException e) {
            logger.error("Network or HTTP Client Error during payment setup: ", e);
            return false;
        } catch (Exception e) {
            logger.error("Unexpected error during payment setup: ", e);
            throw new RuntimeException("System error occurred during payment setup", e);
        }
    }

    public CompletableFuture<Boolean> setupPaymentAsync(InvoiceDTO invoiceDTO) {
        return CompletableFuture.supplyAsync(() -> setupPayment(invoiceDTO));
    }
}