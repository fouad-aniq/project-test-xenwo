package com.example.payment.domain.value_objects;

import com.example.payment.domain.ports.CardDetailsValidator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@AllArgsConstructor
@Component
public class CardDetailsValue {

    private String cardNumber;
    private String expiryMonth;
    private String expiryYear;
    private String cvv;

    @Autowired
    private CardDetailsValidator validator;

    public boolean validateCardDetails() throws CardValidationException {
        return validator.validateCardNumber(cardNumber) &&
               validator.validateExpiryDate(expiryMonth, expiryYear) &&
               validator.validateCvv(cvv);
    }

    public static class CardValidationException extends Exception {
        public CardValidationException(String message) {
            super(message);
        }
    }
}
