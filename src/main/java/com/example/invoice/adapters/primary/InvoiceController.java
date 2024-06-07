package com.example.invoice.adapters.primary;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;
import com.example.invoice.domain.ports.InvoiceUpdatePort;
import com.example.invoice.adapters.primary.dto.InvoiceUpdateRequestDTO;
import com.example.invoice.adapters.primary.dto.InvoiceUpdateResponseDTO;
import com.example.invoice.domain.entities.ChangeLogEntry;

/**
 * Controller for handling invoice updates and retrieving change logs.
 */
@RestController
public class InvoiceController {

    private final InvoiceUpdatePort invoiceUpdatePort;

    /**
     * Constructs an instance with the specified invoice update port.
     */
    public InvoiceController(InvoiceUpdatePort invoiceUpdatePort) {
        this.invoiceUpdatePort = invoiceUpdatePort;
    }

    /**
     * Updates an invoice based on provided details.
     *
     * @param request The invoice update request
     * @return ResponseEntity containing the update result
     */
    @PutMapping("/invoices/update")
    public ResponseEntity<InvoiceUpdateResponseDTO> updateInvoice(@Valid @RequestBody InvoiceUpdateRequestDTO request) {
        var updatedInvoice = invoiceUpdatePort.updateInvoice(request.getInvoiceId(), request);
        if (updatedInvoice == null) {
            return ResponseEntity.badRequest().body(new InvoiceUpd... <truncated for brevity>