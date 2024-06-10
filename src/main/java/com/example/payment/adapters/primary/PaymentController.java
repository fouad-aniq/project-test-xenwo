package com.example.payment.adapters.primary;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.validation.Valid;

import com.example.payment.application.dtos.PaymentDTO;
import com.example.payment.application.dtos.CalculationRequest;
import com.example.payment.application.services.IPaymentApplicationService;
import com.example.payment.domain.exceptions.PaymentValidationException;
import com.example.payment.domain.exceptions.ExternalPaymentException;
import com.example.payment.domain.exceptions.CardValidationException;
import com.example.payment.domain.exceptions.BusinessRuleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class PaymentController {

    private final IPaymentApplicationService paymentApplicationService;
    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @Autowired
    public PaymentController(IPaymentApplicationService paymentApplicationService) {
        this.paymentApplicationService = paymentApplicationService;
    }

    @PostMapping("/process-payment")
    public ResponseEntity<Object> processPayment(@RequestBody @Valid PaymentDTO paymentDTO) {
        logger.info("Processing payment for amount: {} and currency: {}", paymentDTO.getAmount(), paymentDTO.getCurrency());
        try {
            validatePaymentDTO(paymentDTO);
            PaymentDTO result = paymentApplicationService.processPayment(paymentDTO);
            logger.info("Payment processed successfully for transactionId: {}", result.getTransactionId());
            return ResponseEntity.ok(result);
        } catch (PaymentValidationException | CardValidationException e) {
            return handlePaymentException(e);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    @PostMapping("/calculate-totals")
    public ResponseEntity<Object> calculateTotals(@RequestBody @Valid CalculationRequest calculationRequest) {
        logger.info("Calculating totals for subtotal: {}", calculationRequest.getSubtotal());
        try {
            double total = paymentApplicationService.calculateTotal(calculationRequest);
            logger.info("Totals calculated successfully, total: {}", total);
            return ResponseEntity.ok(total);
        } catch (BusinessRuleException e) {
            return handleBusinessRuleException(e);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error calculating totals");
        }
    }

    private ResponseEntity<Object> handlePaymentException(Exception e) {
        logger.error("Handling payment exception: ", e);
        return ResponseEntity.badRequest().body("Payment error: " + e.getMessage());
    }

    private void validatePaymentDTO(PaymentDTO paymentDTO) {
        // Custom validation logic
        logger.debug("Validating payment DTO");
    }

    @ExceptionHandler({ PaymentValidationException.class, ExternalPaymentException.class, CardValidationException.class })
    public ResponseEntity<Object> handleExceptions(Exception e) {
        return handlePaymentException(e);
    }
}