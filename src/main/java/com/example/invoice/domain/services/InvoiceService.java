package com.example.invoice.domain.services;

import com.example.invoice.domain.entities.InvoiceEntity;
import com.example.invoice.domain.ports.InvoiceUpdatePort;
import com.example.invoice.domain.exceptions.InvalidInvoiceUpdateException;
import com.example.invoice.domain.exceptions.InvoiceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Map;

/**
 * Service class for handling invoice operations.
 */
@Service
public class InvoiceService {

    private final InvoiceUpdatePort invoiceUpdatePort;

    /**
     * Constructor for dependency injection.
     * @param invoiceUpdatePort The port used for invoice update operations.
     */
    @Autowired
    public InvoiceService(InvoiceUpdatePort invoiceUpdatePort) {
        this.invoiceUpdatePort = invoiceUpdatePort;
    }

    /**
     * Updates invoice details and logs the changes.
     * @param invoiceId ID of the invoice to be updated.
     * @param updates Map containing the updates.
     * @return Updated invoice entity.
     * @throws InvalidInvoiceUpdateException If the update is invalid.
     * @throws InvoiceNotFoundException If the invoice is not found.
     */
    @Transactional
    public InvoiceEntity updateInvoiceDetails(String invoiceId, Map<String, Object> updates) throws InvalidInvoiceUpdateException, InvoiceNotFoundException {
        InvoiceEntity invoice = new InvoiceEntity(invoiceId);
        invoice.applyUpdates(updates);
        invoice = invoiceUpdatePort.updateInvoice(invoice);
        logInvoiceChange(invoice, "Updated invoice details.");
        return invoice;
    }

    /**
     * Logs each change made to an invoice.
     * @param invoice The invoice entity being logged.
     * @param changeDetail Detail of the change for logging.
     */
    private void logInvoiceChange(InvoiceEntity invoice, String changeDetail) {
    }
}