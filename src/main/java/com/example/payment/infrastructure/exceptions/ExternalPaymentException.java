package com.example.payment.infrastructure.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
@NoArgsConstructor
public class ExternalPaymentException extends Exception {

    private static final Logger logger = LoggerFactory.getLogger(ExternalPaymentException.class);
    private String errorCode;

    /**
     * Constructor for creating an exception with a specific message.
     *
     * @param message the detail message.
     */
    public ExternalPaymentException(String message) {
        super(message);
        logException();
    }

    /**
     * Constructor for creating an exception with both a message and a cause.
     *
     * @param message the detail message.
     * @param cause the throwable cause.
     */
    public ExternalPaymentException(String message, Throwable cause) {
        super(message, cause);
        logException();
    }

    /**
     * Constructor for creating an exception with a message, cause, and specific error code.
     *
     * @param message the detail message.
     * @param cause the throwable cause.
     * @param errorCode a specific error code.
     */
    public ExternalPaymentException(String message, Throwable cause, String errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
        logException();
    }

    /**
     * Logs this exception using the application's configured logging framework.
     */
    private void logException() {
        logger.error("Error occurred in external payment service: " + getMessage(), this);
    }
}