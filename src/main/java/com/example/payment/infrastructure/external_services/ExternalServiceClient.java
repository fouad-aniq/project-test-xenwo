package com.example.payment.infrastructure.external_services;

import com.example.payment.domain.value_objects.PaymentRequest;
import com.example.payment.domain.value_objects.PaymentResponse;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.core.env.Environment;

@Component
public class ExternalServiceClient {

    @Autowired
    private HttpClient httpClient;
    @Autowired
    private Environment env;
    @Autowired
    private Logger apiRequestLogger;

    public ExternalServiceClient() {}

    @Async
    public CompletableFuture<PaymentResponse> sendPaymentRequest(PaymentRequest request) throws IOException, ApiException {
        try {
            initializeConnection(request.getServiceName());
            HttpRequest httpRequest = HttpRequestBuilder.createRequest(request, env.getProperty(request.getServiceName() + ".endpoint"));
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            apiRequestLogger.info("Request sent: " + httpRequest.toString() + " Response received: " + response.body());
            return CompletableFuture.completedFuture(parseResponse(response.body()));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ApiException("Request interrupted: " + e.getMessage());
        } catch (Exception e) {
            apiRequestLogger.severe("Payment processing failed: " + e.getMessage());
            throw new ApiException("Payment processing error: " + e.getMessage());
        }
    }

    private void initializeConnection(String serviceName) {
        apiRequestLogger.info("Initializing connection for " + serviceName);
    }

    private PaymentResponse parseResponse(String responseBody) {
        return new PaymentResponse("tx1234", "success", null); // Simplified response parsing assuming success for illustration.
    }
}
