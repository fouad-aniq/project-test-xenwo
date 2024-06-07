package com.example.invoice.adapters.primary.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InvoiceUpdateRequestDTO {

    @NotBlank(message = "Invoice ID cannot be blank")
    private String invoiceId;

    @NotNull(message = "Client details cannot be null")
    @Valid
    private ClientDetailsDTO clientDetails;

    @Size(min = 1, message = "There must be at least one item to update")
    @Valid
    private List<ItemUpdateDTO> items;

    public String getInvoiceId() {
        if (invoiceId == null) throw new IllegalArgumentException("Invoice ID cannot be null");
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        if (invoiceId == null || invoiceId.trim().isEmpty()) throw new IllegalArgumentException("Invoice ID cannot be null or empty");
        this.invoiceId = invoiceId;
    }

    public ClientDetailsDTO getClientDetails() {
        if (clientDetails == null) throw new IllegalArgumentException("Client details are incomplete");
        return clientDetails;
    }

    public void setClientDetails(ClientDetailsDTO clientDetails) {
        if (clientDetails == null) throw new IllegalArgumentException("Client details cannot be null");
        this.clientDetails = clientDetails;
    }

    public List<ItemUpdateDTO> getItems() {
        if (items == null || items.isEmpty()) throw new IllegalArgumentException("Item list cannot be empty");
        return items;
    }

    public void setItems(List<ItemUpdateDTO> items) {
        if (items == null || items.isEmpty()) throw an IllegalArgumentException("Item list cannot be empty or contain invalid items");
        this.items = items;
    }
}