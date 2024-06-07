package com.example.invoice.domain.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InvoiceEntity {

    private static final Logger logger = LoggerFactory.getLogger(InvoiceEntity.class);

    private String invoiceId;
    private ClientDetails clientDetails;
    private List<InvoiceItem> items;

    public InvoiceEntity(String invoiceId, ClientDetails clientDetails, List<InvoiceItem> items) {
        this.invoiceId = invoiceId;
        this.clientDetails = clientDetails;
        this.items = items;
    }

    public InvoiceEntity(String invoiceId) {
        this.invoiceId = invoiceId;
        this.clientDetails = new ClientDetails();
        this.items = new ArrayList<>();
    }

    public void applyUpdates(Map<String, Object> updates) {
        // Implementation of update logic
    }

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