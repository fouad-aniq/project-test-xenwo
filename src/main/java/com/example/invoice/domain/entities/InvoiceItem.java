package com.example.invoice.domain.entities;

import java.math.BigDecimal;
import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class InvoiceItem {

    private String description;
    private int quantity;
    private BigDecimal price;
    private BigDecimal total;
}