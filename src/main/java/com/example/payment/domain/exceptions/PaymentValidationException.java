package com.example.payment.domain.exceptions;

import lombok.Getter;
import lombok.Setter;

/**
 * Exception class for handling validation errors during payment processing.
 */
@Getter
@Setter
public class PaymentValidationException extends Exception {
    private static final long serialVersionUID = 1L;
    private int errorCode;

    /**
     * Constructor initializing with message only.
     *
     * @param message the detail message.
     */
    public PaymentValidationException(String message) {
        super(message);
    }

    /**
     * Constructor initializing with message and cause.
     *
     * @param message the detail message.
     * @param cause the cause of the exception.
     */
    public PaymentValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor initializing with message and specific error code.
     *
     * @param message the detail message.
     * @param errorCode the specific error code related to the payment validation.
     */
    public PaymentValidationException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    /**
     * Retrieves the error code associated with the validation failure.
     *
     * @return the error code
     */
    public int getErrorCode() {
        return errorCode;
    }

}