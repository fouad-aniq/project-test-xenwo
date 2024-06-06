package com.example.invoiceservice.domain.model;

import java.math.BigDecimal;

/**
 * Defines payment terms including minimum amount and other relevant settings for invoice processing.
 */
public class PaymentTerms {

    /**
     * The minimum payable amount to satisfy the payment terms.
     */
    private BigDecimal minimumAmount;

    /**
     * Retrieves the minimum amount.
     *
     * @return the minimum amount
     */
    public BigDecimal getMinimumAmount() {
        return minimumAmount;
    }

    /**
     * Sets the minimum amount.
     *
     * @param minimumAmount the minimum amount to set
     */
    public void setMinimumAmount(BigDecimal minimumAmount) {
        this.minimumAmount = minimumAmount;
    }
}