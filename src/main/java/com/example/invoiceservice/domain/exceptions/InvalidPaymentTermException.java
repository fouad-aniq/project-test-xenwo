package com.example.invoiceservice.domain.exceptions;

public class InvalidPaymentTermException extends Exception {
    public InvalidPaymentTermException(String message) {
        super(message);
    }

    public InvalidPaymentTermException(String message, Throwable cause) {
        super(message, cause);
    }
}