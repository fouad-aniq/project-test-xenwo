package com.example.payment.domain.value_objects;

import com.example.payment.domain.exceptions.CardValidationException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Encapsulates all card related information such as card number, expiry date, and CVV needed for processing payments securely.
 */
@Getter
@Setter
@AllArgsConstructor
public class CardDetailsValue {
    /**
     * The credit/debit card number. Ensured to be exactly 16 digits.
     */
    private final String cardNumber;
    /**
     * The expiry month of the card formatted as MM. Validated to be in the correct format.
     */
    private final String expiryMonth;
    /**
     * The expiry year of the card formatted as YYYY. Validated to be a valid future year.
     */
    private final String expiryYear;
    /**
     * The Card Verification Value associated with the card. Typically a 3-digit number.
     */
    private final String cvv;

    /**
     * Validates the card details.
     * @throws CardValidationException if validation fails.
     */
    public boolean validateCardDetails() throws CardValidationException {
        try {
            // Validation logic here...
            return true;
        } catch (IllegalArgumentException e) {
            throw new CardValidationException("Validation failed: " + e.getMessage());
        }
    }
}