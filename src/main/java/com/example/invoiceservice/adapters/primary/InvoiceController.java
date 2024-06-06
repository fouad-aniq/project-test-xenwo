package com.example.invoiceservice.adapters.primary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.example.invoiceservice.application.dtos.InvoiceDTO;
import com.example.invoiceservice.application.use_cases.CreateInvoiceUseCase;
import com.example.invoiceservice.adapters.exceptions.GlobalExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class InvoiceController {
    private final CreateInvoiceUseCase createInvoiceUseCase;
    private final GlobalExceptionHandler globalExceptionHandler;
    private static final Logger logger = LoggerFactory.getLogger(InvoiceController.class);

    @Autowired
    public InvoiceController(CreateInvoiceUseCase createInvoiceService, GlobalExceptionHandler globalExceptionHandler) {
        this.createInvoiceUseCase = createInvoiceService;
        this.globalExceptionHandler = globalExceptionHandler;
    }

    public ResponseEntity<InvoiceDTO> createInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        try {
            return ResponseEntity.ok(createInvoiceUseCase.createInvoice(invoiceDTO));
        } catch (Exception e) {
            logger.error("Unexpected error: ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}