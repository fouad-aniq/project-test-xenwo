package com.example.invoiceservice.domain.services;

import com.example.invoiceservice.domain.model.PaymentTerms;
import java.math.BigDecimal;
import com.example.invoiceservice.domain.ports.PaymentTermValidationPort;

public class PaymentTermValidator implements PaymentTermValidationPort {
	public boolean validate(PaymentTerms paymentTerms, BigDecimal amount) {
		if (paymentTerms == null || amount == null) return false;
		return amount.compareTo(BigDecimal.ZERO) > 0 && paymentTerms.getMinimumAmount().compareTo(amount) <= 0;
	}
}