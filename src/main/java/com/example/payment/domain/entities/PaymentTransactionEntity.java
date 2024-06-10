package com.example.payment.domain.entities;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.payment.domain.ports.PaymentTransactionPort;

/**
 * Represents the main entity involved in a payment transaction.
 * This class stores information about the transaction amount, currency, and payment method details.
 */
@Getter
@Setter
public class PaymentTransactionEntity implements PaymentTransactionPort {

    private static final Logger logger = LoggerFactory.getLogger(PaymentTransactionEntity.class);

    private String transactionId;
    @NotNull
    private Double amount;
    private String currency;
    private PaymentMethod paymentMethod;

    public PaymentTransactionEntity(@NotNull String transactionId, @NotNull double amount, @NotNull String currency, @NotNull PaymentMethod paymentMethod) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
        this.transactionId = transactionId;
        this.amount = amount;
        this.currency = currency;
        this.paymentMethod = paymentMethod;
        logger.info("PaymentTransactionEntity created: {}", this);
    }

    public void setAmount(double amount) {
        validateAmount(amount);
        this.amount = amount;
    }

    private void validateAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
    }

}