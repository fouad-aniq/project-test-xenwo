package com.example.payment.infrastructure.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExternalPaymentException extends Exception {

    private static final Logger logger = LoggerFactory.getLogger(ExternalPaymentException.class);
    private String errorCode;

    public ExternalPaymentException(String message) {
        super(message);
    }

    public ExternalPaymentException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExternalPaymentException(String message, Throwable cause, String errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
        logException();
    }

    private void logException() {
        logger.error("Error Code: {}", errorCode);
    }
}