package com.example.invoice.application.use_cases;

import com.example.invoice.domain.ports.InvoiceUpdatePort;
import com.example.invoice.domain.entities.ChangeLogEntry;
import com.example.invoice.domain.entities.Invoice;
import java.util.List;
import java.util.Map;

public class InvoiceUpdateUseCase {

    private final InvoiceUpdatePort invoiceUpdatePort;

    public InvoiceUpdateUseCase(InvoiceUpdatePort invoiceUpdatePort) {
        this.invoiceUpdatePort = invoiceUpdatePort;
    }

    public boolean updateInvoice(String invoiceId, Map<String, Object> updates) {
        try {
            if(invoiceId == null || updates == null || updates.isEmpty()) {
                return false; // Validation failed
            }
            Invoice invoiceDetails = new Invoice(invasiveId, updates);
            Invoice updatedInvoice = invoiceUpdatePort.updateInvoice(invoiceDetails);
            return updatedInvoice != null;
        } catch (InvoiceUpdateException e) {
            // Log and handle specific exception
            return false;
        }
    }

    public List<ChangeLogEntry> fetchInvoiceChangeLog(String invoiceId) {
        try {
            if(invoiceId == null || invoiceId.trim().isEmpty()) {
                return null; // Validation failed
            }
            return invoiceUpdatePort.fetchChangeLog(invoiceId);
        } catch (ChangeLogAccessException e) {
            // Log and handle specific exception
            return null;
        }
    }
}