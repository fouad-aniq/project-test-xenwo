package com.example.invoice.adapters.primary.dto;

import lombok.Data;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class InvoiceUpdateResponseDTO {
    @JsonProperty("success")
    private boolean success;

    @JsonProperty("message")
    private String message;

    @JsonProperty("updatedInvoice")
    private UpdatedInvoiceDTO updatedInvoice;

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        if (message == null || message.trim().isEmpty()) {
            throw new IllegalArgumentException("Message cannot be empty.");
        }
        this.message = message;
    }

    public void setUpdatedInvoice(UpdatedInvoiceDTO updatedInvoice) {
        if (updatedInvoice == null) {
            throw new IllegalArgumentException("Updated invoice details cannot be null.");
        }
        this.updatedInvoice = updatedInvoice;
    }
}