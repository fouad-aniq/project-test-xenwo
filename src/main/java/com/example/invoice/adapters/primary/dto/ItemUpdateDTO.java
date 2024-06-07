package com.example.invoice.adapters.primary.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ItemUpdateDTO {

    private String description;
    private int quantity;
    private BigDecimal price;
}