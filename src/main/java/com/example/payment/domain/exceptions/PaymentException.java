package com.example.payment.domain.exceptions;

import java.io.Serializable;

public class PaymentException extends Exception implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public PaymentException() {
        super();
    }

    public PaymentException(String message) {
        super(message);
    }

    public PaymentException(String message, Throwable cause) {
        super(message, cause);
    }
}
