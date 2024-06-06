package com.example.invoiceservice.domain.enums;

/**
 * Defines the different statuses an invoice can have throughout its lifecycle.
 */
public enum InvoiceStatus {
    /**
     * Indicates that the invoice has been created but not yet processed or paid.
     */
    CREATED("Indicates that the invoice has been created but not yet processed or paid."),
    /**
     * Indicates that the invoice has been sent to the client or supplier.
     */
    SENT("Indicates that the invoice has been sent to the client or supplier."),
    /**
     * Indicates that the invoice has been fully paid.
     */
    PAID("Indicates that the invoice has been fully paid."),
    /**
     * Indicates that the invoice has been cancelled and is no longer valid.
     */
    CANCELLED("Indicates that the invoice has been cancelled and is no longer valid.");

    private final String description;

    InvoiceStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}