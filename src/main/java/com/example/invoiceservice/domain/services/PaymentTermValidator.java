package com.example.invoiceservice.domain.services;

import com.example.invoiceservice.domain.model.PaymentTerms;
import java.math.BigDecimal;
import com.example.invoiceservice.domain.ports.PaymentTermValidationPort;

public class PaymentTermValidator implements PaymentTermValidationPort {
    @Override
    public boolean validate(PaymentTerms paymentTerms, BigDecimal amount) {
        // Example validation logic
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }
        return paymentTerms != null && paymentTerms.getMinimumAmount().compareTo(amount) <= 0;
    }
}