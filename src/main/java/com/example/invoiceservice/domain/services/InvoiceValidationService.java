package com.example.invoiceservice.domain.services;

import com.example.invoiceservice.application.dtos.InvoiceDTO;
import javax.validation.Validator;
import javax.validation.Validation;
import javax.validation.ConstraintViolation;
import javax.validation.ValidationException;
import java.util.Set;

public class InvoiceValidationService {

    private final Validator validator;

    public InvoiceValidationService() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public boolean validateInvoiceData(InvoiceDTO invoiceDTO) throws ValidationException {
        Set<ConstraintViolation<InvoiceDTO>> violations = validator.validate(invoiceDTO);
        if (!violations.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder();
            for (ConstraintViolation<InvoiceDTO> violation : violations) {
                errorMessage.append(violation.getPropertyPath()).append(": ").append(violation.getInvalidValue()).append(", ").append(violation.getMessage()).append("; ");
            }
            throw new ValidationException(errorMessage.toString());
        }
        return true;
    }
}