package com.example.invoice.domain.ports;

import com.example.invoice.domain.entities.Invoice;
import com.example.invoice.domain.entities.ChangeLogEntry;
import java.util.List;

/**
 * Defines the interface for updating invoices and fetching their change logs.
 * This port is used by the application layer to interact with domain services.
 */
public interface InvoiceUpdatePort {

    /**
     * Updates an existing invoice based on the provided invoice details and returns the updated invoice.
     *
     * @param invoiceDetails the details of the invoice to be updated
     * @return the updated invoice
     * @throws InvoiceUpdateException if there is an issue during the update process
     */
    Invoice updateInvoice(Invoice invoiceDetails) throws InvoiceUpdateException;

    /**
     * Retrieves a list of change log entries for a given invoice ID, detailing all modifications made to the invoice.
     *
     * @param invoiceId the ID of the invoice to retrieve change logs for
     * @return a list of change log entries
     * @throws ChangeLogAccessException if there is an issue accessing the change log
     */
    List<ChangeLogEntry> fetchChangeLog(String invoiceId) throws ChangeLogAccessException;
}