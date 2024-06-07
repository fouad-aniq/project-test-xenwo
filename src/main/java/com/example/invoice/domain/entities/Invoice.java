package com.example.invoice.domain.entities;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
@AllArgsConstructor
public class Invoice {

    private static final Logger logger = LoggerFactory.getLogger(Invoice.class);

    private String invoiceId;
    private ClientDetails clientDetails;
    private List<InvoiceItem> items;

    public BigDecimal calculateTotal() {
        if (items == null || items.isEmpty()) {
            logger.info("Item list is empty or null.");
            return BigDecimal.ZERO;
        }
        BigDecimal total = items.stream()
                .filter(item -> item != null && item.getPrice() != null)
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        logger.info("Total calculated: {}", total);
        return total;
    }
}