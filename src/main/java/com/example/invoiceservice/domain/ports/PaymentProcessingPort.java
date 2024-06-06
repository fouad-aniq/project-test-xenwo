package com.example.invoiceservice.domain.ports;

import org.slf4j.Logger;
import com.example.invoiceservice.application.dtos.InvoiceDTO;

/**
 * Interface defining methods for interacting with payment processing systems,
 * crucial for decoupling payment operations from core invoice processing.
 */
public interface PaymentProcessingPort {

    /**
     * Attempts to set up payment for a given invoice.
     * @param invoiceDTO the invoice data transfer object
     * @return true if the payment setup is successful, false otherwise
     */
    boolean setupPayment(InvoiceDTO invoiceDTO);

    /**
     * Validates the payment setup for the given invoice.
     * @param invoiceDTO the invoice data transfer object
     * @return true if validation is successful, false otherwise
     */
    boolean validatePaymentSetup(InvoiceDTO invoiceDTO);

    /**
     * Handles payment setup failures by logging errors and performing necessary rollback operations.
     * @param errorMessage the error message to log
     */
    void handlePaymentFailure(String errorMessage);

    /**
     * Handles issues found during payment validation by notifying relevant services or logging for further action.
     * @param validationMessage the message detailing the validation issue
     */
    void handlePaymentValidation(String validationMessage);
}