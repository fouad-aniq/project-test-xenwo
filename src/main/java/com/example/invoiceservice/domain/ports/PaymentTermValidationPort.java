package com.example.invoiceservice.domain.ports;

import com.example.invoiceservice.domain.model.PaymentTerms;
import java.math.BigDecimal;

public interface PaymentTermValidationPort {
    boolean validate(PaymentTerms paymentTerms, BigDecimal amount);
}