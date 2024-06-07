package com.example.invoice.adapters.primary.dto;

import javax.validation.Valid;
import java.util.List;

public class InvoiceUpdateRequestDTO {
    private String invoiceId;
    private ClientDetailsDTO clientDetails;
    private List<ItemUpdateDTO> items;

    public String getInvoiceId() {
        if (invoiceId == null) throw new IllegalArgumentException("Invoice ID cannot be null.");
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        if (invoiceId == null || invoiceId.trim().isEmpty()) throw new IllegalArgumentException("Invoice ID cannot be null or empty.");
        this.invoiceId = invoiceId;
    }

    public ClientDetailsDTO getClientDetails() {
        if (clientDetails == null) throw new IllegalArgumentException("Client details cannot be incomplete.");
        return clientDetails;
    }

    public void setClientDetails(ClientDetailsDTO clientDetails) {
        if (clientDetails == null) throw new IllegalArgumentException("Client details cannot be incomplete or invalid.");
        this.clientDetails = clientDetails;
    }

    public List<ItemUpdateDTO> getItems() {
        if (items == null || items.isEmpty()) throw new IllegalArgumentException("Items list cannot be empty.");
        return items;
    }

    public void setItems(List<ItemUpdateDTO> items) {
        if (items == null || items.isEmpty()) throw new IllegalArgumentException("Item list cannot be empty or contain invalid items.");
        this.items = items;
    }
}