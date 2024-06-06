package com.example.invoiceservice.domain.value_objects;

import java.math.BigDecimal;
import com.example.invoiceservice.domain.exceptions.InvalidPaymentTermException;
import com.example.invoiceservice.domain.ports.PaymentTermValidationPort;
import com.example.invoiceservice.domain.services.PaymentTermValidator;
import com.example.invoiceservice.domain.model.PaymentTerms;

public class InvoiceValueObject {

    public enum PaymentTerms {
        IMMEDIATE,
        NET_30,
        NET_60
    }

    private PaymentTerms paymentTerms;
    private BigDecimal invoiceAmount;
    private String currency;

    public InvoiceValueObject(PaymentTerms paymentTerms, BigDecimal invoiceAmount, String currency) {
        this.paymentTerms = paymentTerms;
        this.invoiceAmount = invoiceAmount;
        this.currency = currency;
        validateCurrency(currency);
    }

    private void validateCurrency(String currency) {
        // TODO: Implementation to validate currency code against ISO 4217 standard
    }

    public boolean validatePaymentTerms() throws InvalidPaymentTermException {
        PaymentTermValidationPort validator = new PaymentTermValidator();
        return validator.validate(this.paymentTerms, this.invoiceAmount);
    }

    public PaymentTerms getPaymentTerms() {
        return paymentTerms;
    }

    public void setPaymentTerms(PaymentTerms paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    public BigDecimal getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(BigDecimal invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}