package com.example.payment.adapters.primary;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;
import com.example.payment.application.dtos.PaymentDTO;
import com.example.payment.application.dtos.CalculationRequest;
import com.example.payment.application.services.PaymentApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestController
@ControllerAdvice
public class PaymentController {

    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @Autowired
    private PaymentApplicationService paymentApplicationService;

    @PostMapping("/processPayment")
    public ResponseEntity<Object> processPayment(@RequestBody PaymentDTO paymentDTO) {
        if (paymentDTO == null || !paymentDTO.isValid()) {
            return ResponseEntity.badRequest().body("Invalid payment data");
        }
        logger.info("Processing payment for amount: {} and currency: {}", paymentDTO.getAmount(), paymentDTO.getCurrency());
        PaymentDTO processedPayment = paymentApplicationService.processPayment(paymentDTO);
        return ResponseEntity.ok(processedPayment);
    }

    @PostMapping("/calculateTotals")
    public ResponseEntity<Object> calculateTotals(@RequestBody CalculationRequest calculationRequest) {
        if (calculationRequest == null || !calculationRequest.isValid()) {
            return ResponseEntity.badRequest().body("Invalid calculation data");
        }
        logger.info("Calculating totals for subtotal: {}", calculationRequest.getSubtotal());
        CalculationRequest calculatedResult = paymentApplicationService.calculateTotals(calculationRequest);
        return ResponseEntity.ok(calculatedResult);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleExceptions(Exception e) {
        logger.error("Error occurred: ", e);
        return ResponseEntity.badRequest().body("Error: " + e.getMessage());
    }
}
