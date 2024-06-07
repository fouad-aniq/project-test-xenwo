package com.example.invoice.adapters.primary.dto;

import lombok.Data;
import java.util.List;

@Data
public class UpdatedInvoiceDTO {

    private String invoiceId;
    private ClientDetailsDTO clientDetails;
    private List<ItemUpdateDTO> items;
}