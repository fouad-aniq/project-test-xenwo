package com.example.invoice.domain.services;

import com.example.invoice.domain.entities.InvoiceEntity;
import com.example.invoice.domain.ports.InvoiceUpdatePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Map;
import java.util.List;

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
        try {
            InvoiceEntity invoice = invoiceUpdatePort.updateInvoice(invoiceId, updates);
            logInvoiceChange(invoice, "Updated invoice details.");
            return invoice;
        } catch (InvalidInvoiceUpdateException | InvoiceNotFoundException e) {
            // Handle exception appropriately
            throw e;
        }
    }

    /**
     * Logs each change made to an invoice.
     * @param invoice The invoice entity being logged.
     * @param changeDetail Detail of the change for logging.
     */
    private void logInvoiceChange(InvoiceEntity invoice, String changeDetail) {
        // Implement logging logic here, potentially integrating with an external ELK system
        // Example: log.info("Invoice change logged: ", changeDetail);
    }
}