package com.example.payment.domain.ports;

import com.example.payment.application.dtos.PaymentDTO;
import com.example.payment.domain.value_objects.PaymentResponse;
import com.example.payment.domain.exceptions.PaymentInitializationException;
import com.example.payment.domain.exceptions.PaymentExecutionException;

/**
 * Defines an interface for communicating with external payment gateways like Stripe or PayPal.
 * It abstracts core functionalities required from any payment gateway.
 */
public interface PaymentGatewayPort {
    /**
     * Initializes the payment process with specific details needed, such as amount and payment method.
     * Prepares the payment gateway by setting up necessary configurations.
     * Ensures compliance with security protocols such as PCI-DSS.
     *
     * @param paymentDetails the payment details to initialize
     * @throws PaymentInitializationException if there is an issue in initializing the payment, including invalid payment details or configuration errors.
     */
    void initializePayment(PaymentDTO paymentDetails) throws PaymentInitializationException;

    /**
     * Executes the payment transaction using the provided payment details and returns a response object containing transaction status and details.
     * Performs the transaction and indicates success or failure, adhering to strict security measures.
     *
     * @param paymentDetails the payment details for executing the transaction
     * @return PaymentResponse containing the transaction details
     * @throws PaymentExecutionException if there is an error during the payment execution, including communication failures with the payment gateway or transaction denial.
     */
    PaymentResponse executePayment(PaymentDTO paymentDetails) throws PaymentExecutionException;
}