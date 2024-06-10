package com.example.payment.application.dtos;

import com.example.payment.domain.value_objects.CardDetailsValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

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
        validate();
    }

    public void validate() {
        if (transactionId == null || transactionId.trim().isEmpty())
            throw new IllegalArgumentException("Transaction ID cannot be null or empty");
        if (currency == null || currency.trim().isEmpty())
            throw new IllegalArgumentException("Currency cannot be null or empty");
        if (paymentMethod == null || paymentMethod.trim().isEmpty())
            throw new IllegalArgumentException("Payment method cannot be null or empty");
        if (amount <= 0)
            throw new IllegalArgumentException("Amount must be greater than 0");
        if (cardDetails == null) throw new NullPointerException("Card details cannot be null");
        try {
            cardDetails.validateCardDetails();
        } catch (Exception e) {
            logger.error("Card validation error", e);
            throw new PaymentValidationException("Card validation failed", e);
        }
    }

    public String toJson() throws JsonProcessingException {
        return mapper.writeValueAsString(this);
    }

    @Override
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