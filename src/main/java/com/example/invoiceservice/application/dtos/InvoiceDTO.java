package com.example.invoiceservice.application.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Data Transfer Object that facilitates moving invoice information between layers without exposing domain models or compromising data security.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDTO {
    private Long invoiceId;
    private Long clientId;
    private BigDecimal totalAmount;
    private String currency;
    private String paymentTerms;
    private LocalDate creationDate;
    private String status;

    @Override
    public String toString() {
        return "InvoiceDTO{" +
                "invoiceId=" + Objects.toString(invoiceId) +
                ", clientId=" + Objects.toString(clientId) +
                ", totalAmount=" + Objects.toString(totalAmount) +
                ", currency='" + currency + '\'' +
                ", paymentTerms='" + paymentTerms + '\'' +
                ", creationDate=" + Objects.toString(creationDate) +
                ", status='" + status + '\'' +
                '}';
    }
}