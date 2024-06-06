package com.example.invoiceservice.domain.entities;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class InvoiceItem {

    private Long id;
    private String description;
    private int quantity;
    private BigDecimal unitPrice;
    private Long invoiceId;

    /**
     * Calculates the total price of the item by multiplying quantity by unit price.
     *
     * @return Total price as BigDecimal.
     */
    public BigDecimal calculateTotalPrice() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }
}
