package com.example.payment.adapters.primary;

import com.example.payment.application.dtos.PaymentDTO;
import com.example.payment.domain.exceptions.PaymentProcessingException;
import com.example.payment.domain.value_objects.PaymentResponse;
import javax.inject.Named;

/**
 * Interface defining methods for communication between the PaymentController and the PaymentApplicationService.
 *
 * @Named indicates that this interface is a Spring managed component.
 */
@Named
public interface PaymentControllerPort {
    /**
     * Initiates and processes a payment request by passing the necessary data encapsulated in PaymentDTO,
     * facilitating the transaction through PaymentApplicationService and returns a PaymentResponse containing details about the transaction outcome.
     *
     * @param paymentDTO the payment data transfer object
     * @return PaymentResponse with details of the payment transaction
     * @throws PaymentProcessingException if processing fails
     */
    PaymentResponse processPaymentRequest(PaymentDTO paymentDTO) throws PaymentProcessing... (truncated for brevity)