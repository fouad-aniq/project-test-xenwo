package com.example.payment.adapters.primary;

import com.example.payment.application.dtos.PaymentDTO;
import com.example.payment.domain.value_objects.PaymentResponse;
import java.util.Optional;

/**
 * Interface defining the methods required for communication between the PaymentController and the PaymentApplicationService.
 */
public interface PaymentControllerPort {

    /**
     * Initiates and processes a payment request by passing the necessary data encapsulated in PaymentDTO,
     * facilitating the transaction through PaymentApplicationService and returns an Optional<PaymentResponse>
     * containing details about the transaction outcome if present.
     *
     * @param paymentDTO the payment data transfer object containing all necessary payment details
     * @return an Optional<PaymentResponse> containing the transaction outcome details if present
     */
    Optional<PaymentResponse> processPaymentRequest(PaymentDTO paymentDTO);
}