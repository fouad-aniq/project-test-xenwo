package com.example.invoiceservice.domain.services;

import java.math.BigDecimal;

public interface PaymentTermValidationPort {
    boolean validate(com.example.invoiceservice.domain.value_objects.InvoiceValueObject.PaymentTerms paymentTerms, BigDecimal amount);
}