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
    public PaymentSeems there's a typo in the second constructor where 'errorKey' is used instead of 'errorCode'. Also, using Lombok's @AllArgsConstructor could simplify construction further. Additionally, if JSON serialization of 'errorCode' and 'message' needs specific handling, it might require implementation.

    /**
     * Constructor initializing message and cause of the exception, with default error code.
     * @param message Detailed error message
     * @param cause the cause (can be null)
     */
    public PaymentValidationException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = 500; // Default error code
    }

    /**
     * Retrieves the error code associated with the validation failure.
     * @return error code
     */
    public int getErrorCode() {
        return errorCode;
    }
}