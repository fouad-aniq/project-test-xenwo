package com.example.payment.infrastructure.external_services;

import com.example.payment.domain.value_objects.PaymentRequest;
import com.example.payment.domain.value_objects.PaymentResponse;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import java.util.logging.Logger;
import com.example.payment.domain.ports.PaymentGatewayClientPort;

public class ExternalServiceClient implements PaymentGatewayClientPort {

    private HttpClient httpClient;
    private Map<String, String> apiKeys;
    private Logger apiRequestLogger;

    @Value("${payment.gateway.api.url}")
    private String apiUrl;

    public ExternalServiceClient(HttpClient httpClient, Map<String, String> apiKeys, Logger apiRequestLogger) {
        this.httpClient = httpClient;
        this.apiKeys = apiKeys;
        this.apiRequestLogger = apiRequestLogger;
        initializeConnection();
    }

    @Override
    public PaymentResponse sendPaymentRequest(PaymentRequest request) throws IOException, InterruptedException {
        validateRequest(request);
        HttpRequest httpRequest = HttpRequest.newBuilder()
            .uri(java.net.URI.create(apiUrl + "/payments"))
            .headers("Content-Type", "application/json", "Authorization", "Bearer " + apiKeys.get(request.getPaymentInfo().getPaymentMethod()))
            .POST(HttpRequest.BodyPublishers.ofString(request.toJson()))
            .timeout(java.time.Duration.ofMinutes(2)) // Timeout set to 2 minutes
            .build();
        try {
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            logResponse(response);
            return parseResponse(response);
        } catch (HttpRequestException e) {
            apiRequestLogger.severe("HTTP request failed: " + e.getMessage());
            throw new PaymentProcessingException("Failed to process payment due to HTTP error", e);
        }
    }

    private void initializeConnection() {
        apiRequestLogger.info("Initializing connection with API URL: " + apiUrl);
        // Additional configurations for different gateways could be added here based on the apiKeys
    }

    private void validateRequest(PaymentRequest request) {
        if (request == null || request.getPaymentInfo() == null) {
            throw new IllegalArgumentException("Invalid payment request data.");
        }
        // Further validations can be added here
    }

    private void logResponse(HttpResponse<String> response) {
        apiRequestLogger.info("Received response: Status=" + response.statusCode() + ", Body=" + response.body());
        if (response.statusCode() != 200) {
            apiRequestLogger.warning("Request failed: " + response.body());
        }
    }

    private PaymentResponse parseResponse(HttpResponse<String> response) throws IOException {
        if (response.statusCode() != 200) {
            throw new PaymentProcessingException("Payment processing failed with status: " + response.statusCode());
        }
        return new PaymentResponse("transaction123", "success", null); // Simplified example assuming JSON parsing
    }
}