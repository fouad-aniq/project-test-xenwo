package com.example.invoiceservice.application.use_cases;

import com.example.invoiceservice.domain.services.InvoiceService;
import com.example.invoiceservice.domain.services.InvoiceValidationService;
import com.example.invoiceservice.domain.ports.InvoiceRepositoryPort;
import com.example.invoiceservice.domain.ports.PaymentProcessingPort;
import com.example.invoiceservice.application.dtos.InvoiceDTO;
import com.example.invoiceservice.domain.exceptions.DatabaseAccessException;
import com.example.invoiceservice.domain.exceptions.InvoiceValidationException;
import com.example.invoiceservice.domain.exceptions.PaymentProcessingException;

public class CreateInvoiceUseCase {

    private final InvoiceService invoiceService;
    private final InvoiceValidationService invoiceValidationService;
    private final InvoiceRepositoryPort invoiceRepositoryPort;
    private final PaymentProcessingPort paymentProcessingPort;

    public CreateInvoiceUseCache(InvoiceService invoiceService, InvoiceValidationService invoiceValidationService, InvoiceRepositoryPort invoiceRepositoryPort, PaymentProcessingPort paymentProcessingPort) {
        this.invoiceService = invoiceService;
        this.invoiceValidationService = invoiceValidationService;
        this.invoiceRepositoryPort = invoiceRepositoryPort;
        this.paymentProcessingPort = paymentProcessingPort;
    }

    public InvoiceDTO createInvoice(InvoiceDTO invoiceDto) throws InvoiceValidationException, PaymentProcessingException, DatabaseAccessException {
        if (!invoiceValidationService.validateInvoiceData(invoiceDto)) {
            throw new InvoiceValidationException("Invalid invoice data provided.");
        }

        // Start transaction
        invoiceRepositoryPort.beginTransaction();
        try {
            InvoiceDTO processedInvoice = invoiceService.createInvoice(invoiceDto);
            if (paymentProcessingPort.setupPayment(processedInvoice)) {
                invoiceRepositoryPort.commitTransaction();
                return processedInvoice;
            } else {
                throw new PaymentProcessingException("Payment setup failed");
            }
        } catch (Exception e) {
            invoiceRepositoryPort.rollbackTransaction();
            throw e;
        }
    }
}