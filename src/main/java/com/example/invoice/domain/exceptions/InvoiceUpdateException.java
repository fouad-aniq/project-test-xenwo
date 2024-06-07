package com.example.invoice.domain.exceptions;

public class InvoiceUpdateException extends RuntimeException {

    public InvoiceUpdateException(String message) {
        super(message);
    }

    public InvoiceUpdateException(String message, Throwable cause) {
        super(message, cause);
    }
}