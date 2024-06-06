package com.example.invoiceservice.domain.exceptions;

import java.io.Serializable;

public class DatabaseAccessException extends Exception implements Serializable {

    private static final long serialVersionUID = 1L;

    public DatabaseAccessException(String message) {
        super(message);
    }

    public DatabaseAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}