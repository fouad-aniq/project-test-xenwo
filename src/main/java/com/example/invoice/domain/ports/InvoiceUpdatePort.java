package com.example.invoice.domain.ports;

import com.example.invoice.domain.entities.InvoiceEntity;
import com.example.invoice.domain.exceptions.InvoiceUpdateException;
import com.example.invoice.domain.exceptions.ChangeLogAccessException;
import com.example.invoice.domain.entities.ChangeLogEntry;
import java.util.List;

public interface InvoiceUpdatePort {

    InvoiceEntity updateInvoice(InvoiceEntity invoiceDetails) throws InvoiceUpdateException;

    List<ChangeLogEntry> fetchChangeLog(String invoiceId) throws ChangeLogAccessException;
}