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
        BigDecimal total = BigDecimal.ZERO;
        if (items != null) {
            for (InvoiceItem item : items) {
                if (item != null && item.getPrice() != null) {
                    total = total.add(item.getPrice().multiply(new BigDecimal(item.getQuantity())));
                }
            }
        }
        logger.info("Total calculated: {}", total);
        return total;
    }
}