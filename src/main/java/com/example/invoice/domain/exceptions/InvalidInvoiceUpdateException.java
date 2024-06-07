package com.example.invoice.domain.exceptions;

public class InvalidInvoiceUpdateException extends Exception {
    public InvalidInvoiceUpdateException(String message) {
        super(message);
    }

    public InvalidInvoiceUpdateException(String message, Throwable cause) {
        super(message, cause);
    }
}