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

    public InvoiceUpdateResponseDTO(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public InvoiceUpdateResponseDTO(boolean success, String message, UpdatedInvoiceDTO updatedInvoice) {
        this.success = success;
        this.message = message;
        this.updatedInvoice = updatedInvoice;
    }
}