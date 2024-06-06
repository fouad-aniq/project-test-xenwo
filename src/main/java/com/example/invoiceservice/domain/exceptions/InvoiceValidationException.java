package com.example.invoiceservice.domain.exceptions;

public class InvoiceValidationException extends Exception {
    public InvoiceValidationException(String message) {
        super(message);
    }
    public InvoiceValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}