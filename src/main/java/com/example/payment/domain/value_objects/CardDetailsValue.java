package com.example.payment.domain.value_objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.payment.domain.ports.CardDetailsValidator;
import com.example.payment.domain.exceptions.CardValidationException;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Encapsulates all card related information such as card number, expiry date, and CVV needed for processing payments securely.
 */
@Getter
@AllArgsConstructor
public class CardDetailsValue {
    private static final Logger logger = LoggerFactory.getLogger(CardDetailsValue.class);
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
    private final CardDetailsValidator cardDetailsValidator;

    /**
     * Validates the card details using the injected CardetailsValidator.
     * @return true if validation is successful, otherwise false.
     * @throws CardValidationException if validation fails.
     */
    public boolean validateCardDetails() throws CardValidationException {
        logger.info("Starting validation for card details.");
        try {
            return cardDetailsValidator.validate(this);
        } catch (IllegalArgumentException e) {
            logger.error("Validation failed due to invalid input: ", e);
            throw new CardValidationException("Validation failed: " + e.getMessage());
        } finally {
            logger.info("Validation completed.");
        }
    }
}