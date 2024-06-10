package com.example.payment.adapters.primary;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.payment.application.dtos.PaymentDTO;
import com.example.payment.application.services.PaymentApplicationService;

@RestController
public class PaymentController {

    @Autowired
    private PaymentApplicationService paymentApplicationService;

    @PostMapping("/payments")
    public ResponseEntity<Object> processPayment(@RequestBody PaymentDTO paymentDTO) {
        return ResponseEntity.ok(paymentApplicationService.processPayment(paymentDTO));
    }

    // Other methods unchanged
}