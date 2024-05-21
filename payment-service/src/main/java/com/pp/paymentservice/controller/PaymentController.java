package com.pp.paymentservice.controller;

import com.pp.paymentservice.model.Payment;
import com.pp.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/paypal")
@CrossOrigin(origins = "http://localhost:4200")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Payment> createPayment(@RequestParam("orderId") Long orderId,
                                                 @RequestParam("totalCost") BigDecimal totalCost,
                                                 @RequestParam("paymentMethodNonce") String paymentMethodNonce) {
        var payment = paymentService.makePayment(orderId, totalCost, paymentMethodNonce);
        return ResponseEntity.of(payment);
    }
}
