package com.example.payment.domain.entities;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf3b.LoggerFactory;
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
    private double amount;
    private String currency;
    private PaymentMethod paymentMethod;

    public PaymentTransactionEntity(@NotNull String transactionId, @NotNull double amount, @NotNull String currency, @NotNull PaymentMethod paymentMethod) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
        try {
            this.paymentMethod = PaymentMethod.fromString(paymentMethod);
        } catch (IllegalArgumentException e) {
            logger.error("Invalid payment method: {}, error: {}", paymentMethod, e.getMessage());
            throw e;
        }
        this.transactionId = transactionId;
        this.amount = amount;
        this.currency = validateCurrency(currency);
        logger.info("PaymentTransactionEntity created: {}", this);
    }

    private String validateCurrency(String currency) {
        List<String> validCurrencies = Arrays.asList("USD", "EUR");
        if (!validCurrencies.contains(currency)) {
            throw new IllegalArgumentException("Invalid currency code: " + currency);
        }
        return currency;
    }

    public void setAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
        double oldAmount = this.amount;
        this.amount = amount;
        logger.debug("Amount changed from {} to {} on {}", oldAmount, amount, LocalDateTime.now());
    }
}