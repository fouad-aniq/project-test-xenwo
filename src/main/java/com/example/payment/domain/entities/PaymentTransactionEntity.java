package com.example.payment.domain.entities;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents the main entity involved in a payment transaction.
 * This class stores information about the transaction amount, currency, and payment method details.
 */
@Getter
@Setter
public class PaymentTransactionEntity {

    private static final Logger logger = LoggerFactory.getLogger(PaymentTransactionEntity.class);

    private String transactionId;
    private Double amount;
    private String currency;
    private PaymentMethod paymentMethod;

    public PaymentTransactionEntity(String transactionID, double amount, String currency, PaymentMethod paymentMethod) {
        this.transactionID = transactionID;
        this.amount = amount;
        this.currency = currency;
        this.paymentMethod = paymentMethod;
        logger.info("PaymentTransactionEntity created: {}", this);
    }

    public void setAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
        this.amount = amount;
    }
}