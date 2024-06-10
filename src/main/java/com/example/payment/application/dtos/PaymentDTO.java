package com.example.payment.application.dtos;

import com.example.payment.domain.value_objects.CardDetailsValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
@AllArgsConstructor
@ToString
public class PaymentDTO implements PaymentDataTransferObject {

    private static final Logger logger = LoggerFactory.getLogger(PaymentDTO.class);

    private String transactionId;
    private double amount;
    private String currency;
    private String paymentMethod;
    private CardDetailsValue cardDetails;

    public String toJson() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            logger.error("Error serializing PaymentDTO", e);
            throw new RuntimeException("Error serializing PaymentDTO", e);
        }
    }

    public void validate() {
        if (transactionId == null || amount <= 0 || currency == null || paymentMethod == null || cardDetails == null) {
            throw new IllegalArgumentException("Invalid payment details");
        }
        try {
            cardDetails.validateCardDetails();
        } catch (CardDetailsValue.CardValidationException e) {
            logger.error("Card validation failed", e);
            throw new RuntimeException("Card validation error: " + e.getMessage(), e);
        }
    }
}
