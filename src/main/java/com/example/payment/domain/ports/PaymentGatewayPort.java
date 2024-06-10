package com.example.payment.domain.ports;

import com.example.payment.application.dtos.PaymentDTO;
import com.example.payment.domain.value_objects.PaymentResponse;
import com.example.payment.domain.exceptions.PaymentInitializationException;
import com.example.payment.domain.exceptions.PaymentExecutionException;

/**
 * Interface for communicating with external payment gateways like Stripe or PayPal.
 * It abstracts core functionalities required from any payment gateway.
 *
 * Implementations should handle all communication securely and must comply with PCI-DSS standards.
 */
public interface PaymentGatewayPort {

    /**
     * Initializes the payment process with specific details needed, such as amount and payment method.
     * Prepares the payment gateway by setting up necessary configurations.
     * Ensures compliance with security protocols such as PCI-DSS.
     * 
     * @param paymentDetails The payment details to initialize.
     * @throws PaymentInitializationException If there are any issues during initialization.
     */
    void initializePayment(PaymentDTO paymentDetails) throws PaymentInitializationException;

    /**
     * Executes the payment transaction using the provided payment details and returns a response object containing transaction status and details.
     * Performs the transaction and indicates success or failure, adhering to strict security measures.
     * 
     * @param paymentDetails The payment details to execute.
     * @throws PaymentExecutionException If there is an error during the execution of the transaction.
     * @return PaymentResponse The response from the payment transaction.
     */
    PaymentResponse executePayment(PaymentDTO paymentDetails) throws PaymentExecutionException;
}