package com.example.payment.domain.entities;

/**
 * Interface defining behavior for payment methods.
 */
public interface PaymentMethodBehavior {
    String getDescription();
}

/**
 * Enumeration defining various possible payment methods used across transactions in the payment system.
 * This helps in maintaining a type-safe way of handling payment options throughout the domain.
 */
public enum PaymentMethod implements PaymentMethodBehavior {
    /**
     * Payment made using a credit card. Commonly used due to its convenience and widespread acceptance.
     */
    CREDIT_CARD("Payment made using a credit card. Commonly used due to its convenience and widespread acceptance."),
    /**
     * Payment made using a debit card. Directly debits funds from the user's bank account.
     */
    DEBIT_CARD("Payment made using a debit card. Directly debits funds from the user's bank account."),
    /**
     * Payment made through PayPal service. Offers secure and quick transactions online.
     */
    PAYPAL("Payment made through PayPal service. Offers secure and quick transactions online."),
    /**
     * Payment made via direct bank transfer. Used for larger transactions or where card payments are not feasible.
     */
    BANK_TRANSFER("Payment made via direct bank transfer. Used for larger transactions or where card payments are not feasible.");

    private final String description;

    PaymentMethod(String description) {
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Converts a string value to a Payment Method enum constant.
     * @param method The string representation of the payment method.
     * @throws IllegalArgumentException if the specified method does not match any enum constant.
     * @return The corresponding PaymentMethod enum constant.
     */
    public static PaymentMethod fromString(String method) {
        for (PaymentMethod pm : values()) {
            if (pm.name().equalsIgnoreCase(method)) {
                return pm;
            }
        }
        throw new IllegalArgumentException("Unknown payment method: " + method);
    }

    /**
     * Returns the string representation of this payment method.
     * @return A string describing the payment method.
     */
    @Override
    public String toString() {
        return name() + ": " + description;
    }
}