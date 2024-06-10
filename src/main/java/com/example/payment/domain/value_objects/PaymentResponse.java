package com.example.payment.domain.value_objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * A class representing the response received from a payment gateway upon the successful or failed execution of a payment transaction.
 * Includes details such as transaction ID, status, and error messages if applicable.
 */
@Getter
@Setter
@AllArgsConstructor
public class PaymentResponse {

    /**
     * Unique identifier for the payment transaction.
     */
    private String transactionId;

    /**
     * Status of the payment transaction, which could be 'success', 'failed', or other relevant statuses.
     */
    private String status;

    /**
     * Message detailing any errors that occurred during the payment process. Null if the transaction was successful.
     */
    private String errorMessage;

    /**
     * Validates the status to ensure it is one of the predefined values.
     * @param status The status to validate.
     * @return boolean indicating if the status is valid.
     */
    public boolean validateStatus(String status) {
        return status != null && (status.equals("success") || status.equals("failed"));
    }

    /**
     * Generates a receipt URL based on the transaction ID if the transaction is successful.
     * @return String representing the URL of the receipt.
     */
    public String generateReceiptURL() {
        if (this.status.equals("success")) {
            return "https://example.com/receipts/" + this.transactionId + ".pdf";
        }
        return null;
    }
}