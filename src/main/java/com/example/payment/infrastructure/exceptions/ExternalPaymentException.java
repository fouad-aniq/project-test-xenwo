package com.example.payment.infrastructure.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
@NoArgsConstructor
public class ExternalPaymentException extends Exception {

    private static final Logger logger = LoggerFactory.getLogger(ExternalPaymentDateException.class);
    private String errorCode;

    public ExternalPaymentException(String message) {
        super(message);
        logException();
    }

    public ExternalPaymentException(String message, Throwable cause) {
        super(message, cause);
        logException();
    }

    public ExternalPaymentException(String message, Throwable cause, String errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
        logException();
    }

    private void logException() {
        logger.error("Error occurred in external payment service: " + getMessage(), this);
    }
}