package com.example.invoice.adapters.primary;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import java.util.List;
import com.example.invoice.domain.entities.ChangeLogEntry;
import com.example.invoice.adapters.primary.dto.InvoiceUpdateRequestDTO;
import com.example.invoice.adapters.primary.dto.InvoiceUpdateResponseDTO;
import com.example.invoice.domain.entities.InvoiceEntity;
import com.example.invoice.domain.ports.InvoiceUpdatePort;

@RestController
@Validated
public class InvoiceController {

    @Autowired
    private InvoiceUpdatePort invoiceUpdatePort;

    /**
     * Updates invoice details based on the provided request
     *
     * @param request DTO containing invoice update information
     * @return ResponseEntity with updated invoice response
     */
    @PutMapping("/invoices")
    public ResponseEntity<InvoiceUpdateResponseDTO> updateInvoice(@Valid @RequestBody InvoiceUpdateRequestDTO request) {
        InvoiceEntity updatedInvoice = invoiceUpdatePort.updateInvoice(new InvoiceEntity(request.getInvoiceId(), request.getClientDetails(), request.getItems()));
        if (updatedInvoice == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new InvoiceUpdateResponseDTO(true, "Invoice updated successfully", updatedInvoice));
    }

    /**
     * Fetches change logs for an invoice based on the ID of the invoice to retrieve change logs for
     * @param invoiceId the ID of the invoice to retrieve change logs for
     * @return ResponseEntity containing a list of change log entries
     */
    @GetMapping("/invoices/changelog")
    public ResponseEntity<List<ChangeLogEntry>> getInvoiceChangeLog(@RequestParam String invoiceId) {
        List<ChangeLogEntry> changeLogs = invoiceUpdatePort.fetchChangeLog(invoiceId);
        return ResponseEntity.ok(changeLogs);
    }
}