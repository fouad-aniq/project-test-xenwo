package com.example.payment.domain.entities;

/**
 * Enumeration defining various possible payment methods used across transactions in the payment system.
 * This helps in maintaining a type-safe way of handling payment options throughout the domain.
 */
public enum PaymentMethod {
    /**
     * Payment made using a credit card. Commonly used due to its convenience and widespread acceptance.
     */
    CREDIT_CARD,

    /**
     * Payment made using a debit card. Directly debits funds from the user's bank account.
     */
    DEBIT_CARD,

    /**
     * Payment made through PayPal service. Offers secure and quick transactions online.
     */
    PAYPAL,

    /**
     * Payment made via direct bank transfer. Used for larger transactions or where card payments are not feasible.
     */
    BANK_TRANSFER
}