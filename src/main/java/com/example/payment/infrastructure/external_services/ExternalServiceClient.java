package com.example.payment.infrastructure.external_services;

import com.example.payment.domain.ports.PaymentGatewayClientPort;
import com.example.payment.domain.exceptions.PaymentProcessingException;
import com.example.payment.domain.value_objects.PaymentRequest;
import com.example.payment.domain.value_objects.PaymentResponse;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ExternalServiceClient implements PaymentGatewayClientPort {

    private static final Logger logger = LoggerFactory.getLogger(ExternalServiceClient.class);
    private HttpClient httpClient;
    private Map<String, String> apiKeys;

    @Autowired
    public ExternalServiceClient(HttpClient httpClient, Map<String, String> apiKeys) {
        this.httpClient = httpClient;
        this.apiKeys = apiKeys;
    }

    public PaymentResponse sendPaymentRequest(PaymentRequest request) throws IOException, InterruptedException, PaymentProcessingException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
            .uri(URI.create("https://api.paymentgateway.com/charge"))
            .header("Authorization", "Bearer " + apiKeys.get("authKey"))
            .POST(HttpRequest.BodyPublishers.ofString(request.toJson()))
            .build();

        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        return new PaymentResponse(httpResponse.body());
    }
}