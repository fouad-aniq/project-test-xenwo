package com.example.invoice.application.use_cases;

import com.example.invoice.domain.ports.InvoiceUpdatePort;
import com.example.invoice.domain.entities.ChangeLogEntry;
import com.example.invoice.domain.entities.Invoice;
import com.example.invoice.domain.exceptions.InvoiceUpdateException;
import com.example.invoice.domain.exceptions.ChangeLogAccessException;
import java.util.List;
import java.util.Map;

public class InvoiceUpdateUseCase {

    private final InvoiceUpdatePort invoiceUpdatePort;

    public InvoiceUpdateUseCase(InvoiceUpdatePort invoiceUpdatePort) {
        this.invoiceUpdatePort = invoiceUpdatePort;
    }

    // Rest of the methods...
}