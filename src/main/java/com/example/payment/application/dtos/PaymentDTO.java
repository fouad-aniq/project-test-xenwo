package com.example.payment.application.dtos;

import com.example.payment.domain.value_objects.CardDetailsValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Data Transfer Object used to transfer payment data between different parts of the application, especially between the controllers and the use cases.
 */
@Component
public class PaymentDTO {

    private static final Logger logger = LoggerFactory.getLogger(PaymentDTO.class);

    @Autowired
    private ObjectMapper mapper;

    private String transactionId;
    private double amount;
    private String currency;
    private String paymentMethod;
    private CardDetailsValue cardDetails;

    public PaymentDTO(String transactionId, double amount, String currency, String paymentMethod, CardDetailsValue cardDetails) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.currency = currency;
        this.paymentMethod = paymentMethod;
        this.cardDetails = cardDetails;
    }

    public String toJson() throws JsonProcessingException {
        return mapper.writeValueAsString(this);
    }

    public void validate() {
        // Validation logic here
    }

    public String toString() {
        return "PaymentDTO{" +
                "transactionId='" + transactionId + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", cardDetails=" + cardDetails +
                '}';
    }
}