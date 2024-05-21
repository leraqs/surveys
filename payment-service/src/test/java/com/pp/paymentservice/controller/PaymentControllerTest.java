package com.pp.paymentservice.controller;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.PaymentMethodNonce;
import com.braintreegateway.Result;
import com.pp.paymentservice.config.PayPalConfig;
import com.pp.paymentservice.model.Payment;
import com.pp.paymentservice.repository.PaymentRepository;
import com.pp.paymentservice.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@Import(PayPalConfig.class)
class PaymentControllerTest {

    @MockBean
    PaymentRepository paymentRepository;
    @Autowired
    BraintreeGateway gateway;
    @Autowired
    private PaymentService paymentService;

    @Test
    public void test_successful_payment_returns_correct_response() {
        Long orderId = 1L;
        BigDecimal totalCost = new BigDecimal("100.00");
        Payment expectedPayment = new Payment();
        String nonce = "fake-valid-nonce";

        expectedPayment.setOrderId(orderId);

        when(paymentRepository.save(any())).thenReturn(expectedPayment);

        var payment = paymentService.makePayment(orderId, totalCost, nonce);

        assertFalse(payment.isEmpty());
        assertEquals(orderId, payment.get().getOrderId());
    }
}