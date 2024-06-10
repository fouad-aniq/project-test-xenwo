package com.example.payment.domain.exceptions;

import java.io.Serializable;
import java.io.Serial;

public class PaymentException extends Exception implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public PaymentRequest() {
        super();
    }

    public PaymentRequest(String message) {
        super(message);
    }

    public PaymentRequest(String message, Throwable cause) {
        super(message, cause);
    }
}
