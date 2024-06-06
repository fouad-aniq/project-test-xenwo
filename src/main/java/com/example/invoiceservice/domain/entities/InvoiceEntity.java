package com.example.invoiceservice.domain.entities;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.ValidationException;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "invoices")
@Data
@NoArgsConstructor
public class InvoiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long clientId;

    @OneToMany(mappedBy = "invoiceId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvoiceItem> invoiceItems;

    @Column(nullable = false)
    private String paymentTerms;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    private InvoiceStatus status;

    public BigDecimal calculateTotalAmount() throws ValidationException {
        if (invoiceItems == null || invoiceItems.isEmpty()) {
            throw new ValidationException("Invoice items are empty.");
        }
        return invoiceItems.stream()
            .map(InvoiceItem::calculateTotalPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void updateStatus(InvoiceStatus status) {
        this.status = status;
    }
}