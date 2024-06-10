package com.example.payment.domain.entities;

import lombok.Data;

@Data
public class PaymentTransactionEntity {

    private String transactionId;
    private double amount;
    private String currency;
    private PaymentMethod paymentMethod;

    public PaymentTransactionEntity(String transactionId, double amount, String currency, PaymentMethod paymentMethod) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.currency = currency;
        this.paymentMethod = paymentMethod;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    // Implementations for new enhancements
    // Placeholder for interface implementations and service classes
}