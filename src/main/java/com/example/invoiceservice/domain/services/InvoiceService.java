package com.example.invoiceservice.domain.services;

import com.example.invoiceservice.application.dtos.InvoiceDTO;
import com.example.invoiceservice.domain.entities.InvoiceEntity;
import com.example.invoiceservice.domain.exceptions.DatabaseAccessException;
import com.example.invoiceservice.domain.exceptions.InvoiceValidationException;
import com.example.invoiceservice.domain.exceptions.PaymentProcessingException;
import com.example.invoiceservice.domain.ports.InvoiceRepositoryPort;
import com.example.invoiceservice.domain.ports.PaymentProcessingPort;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {

    private final InvoiceValidationService invoiceValidationService;
    private final InvoiceRepositoryPort invoiceRepositoryPort;
    private final PaymentProcessingPort paymentProcessingPort;

    public InvoiceService(InvoiceValidationService invoiceValidationService, InvoiceRepositoryPort invoiceRepositoryPort, PaymentProcessingPort paymentProcessingPort) {
        this.invoiceValidationService = invoiceValidationService;
        this.invoiceRepositoryPort = invoiceRepositoryPort;
        this.paymentProcessingPort = paymentProcessingPort;
    }

    public InvoiceEntity createInvoice(InvoiceDTO invoiceDTO) throws InvoiceValidationException, PaymentProcessingException, DatabaseAccessException {
        if (!invoiceValidationService.validateInvoiceData(invoiceDTO)) {
            throw new InvoiceValidationException("Validation failed for the given invoice data.");
        }
        InvoiceEntity invoice = new InvoiceEntity(invoiceDTO);
        try {
            invoiceRepositoryPort.beginTransaction();
            InvoiceEntity savedInvoice = invoiceRepositoryPort.save(invoice);
            if (paymentProcessingPort.setupPayment(invoiceDTO)) {
                invoiceRepositoryPort.commitTransaction();
                return savedInvoice;
            } else {
                invoiceRepositoryPort.rollbackTransaction();
                throw new PaymentProcessingException("Failed to setup payment.");
            }
        } catch (DatabaseAccessException dae) {
            handleInvoiceCreationFailure(dae);
            throw dae;
        } catch (Exception e) {
            invoiceRepositoryPort.rollbackTransaction();
            throw new RuntimeException("Failed to process invoice creation: " + e.getMessage(), e);
        }
    }

    private void handleInvoiceCreationFailure(Exception e) {
        try {
            invoiceRepositoryPort.rollbackTransaction();
        } catch (Exception ex) {
            // Detailed log of the exception
        }
        throw new RuntimeException("Failed to process invoice creation: " + e.getMessage(), e);
    }
}