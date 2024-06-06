package com.example.invoiceservice.adapters.primary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.invoiceservice.application.use_cases.CreateInvoiceUseCase;
import com.example.invoiceservice.application.dtos.InvoiceDTO;
import com.example.invoiceservice.domain.exceptions.DatabaseAccessException;
import com.example.invoiceservice.domain.exceptions.InvoiceValidationException;

@RestController
public class InvoiceController {

    private static final Logger logger = LoggerFactory.getLogger(InvoiceController.class);

    @Autowired
    private CreateInvoiceUseCase createInvoiceUseCase;

    @PostMapping("/invoices")
    public ResponseEntity<InvoiceDTO> createInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        try {
            InvoiceDTO createdInvoice = createInvoiceUseCase.createInvoice(invoiceDTO);
            return new ResponseEntity<>(createdInvoice, HttpStatus.CREATED);
        } catch (InvoiceValidationException | DatabaseAccessException e) {
            logger.error("Error processing invoice: ", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Unexpected error: ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}