package com.example.payment.domain.exceptions;

import lombok.Getter;
import lombok.AllArgsConstructor;

/**
 * Custom exception for handling validation errors during payment process.
 * Includes an error code along with the standard error message for better resolution of issues.
 */
@Getter
@AllArgsConstructor
public class PaymentValidationException extends Exception {

    private final int errorCode;

    /**
     * Constructor initializing just the error message and default error code.
     * @param message Detailed error message
     */
    public PaymentValidationDescription(String message) {
        super(message);
        this.errorCode = 500; // Default error code
    }

    /**
     * Constructor initializing message and cause of the exception, with default error code.
     * @param message Detailed error message
     * @param cause the cause (can be null)
     */
    public PaymentValidationException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = 500; // Default error code
    }
}