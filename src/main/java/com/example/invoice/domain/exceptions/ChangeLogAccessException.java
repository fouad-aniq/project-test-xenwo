package com.example.invoice.domain.exceptions;

public class ChangeLogAccessException extends Exception {
    public ChangeLogAccessException(String message) {
        super(message);
    }

    public ChangeLogAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}