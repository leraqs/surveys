package com.pp.paymentservice.controller;

import com.braintreegateway.BraintreeGateway;
import com.pp.paymentservice.model.Payment;
import com.pp.paymentservice.repository.PaymentRepository;
import com.pp.paymentservice.service.PaymentService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.OracleContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@Testcontainers
class PaymentControllerTest {

    @Container
    static OracleContainer oracleContainer = new OracleContainer("gvenzl/oracle-xe:21-slim-faststart")
            .withUsername("test")
            .withPassword("test")
            .withDatabaseName("testdb");

    @MockBean
    private PaymentRepository paymentRepository;

    @Autowired
    private BraintreeGateway gateway;

    @Autowired
    private PaymentService paymentService;

    @DynamicPropertySource
    static void registerOracleProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", oracleContainer::getJdbcUrl);
        registry.add("spring.datasource.username", oracleContainer::getUsername);
        registry.add("spring.datasource.password", oracleContainer::getPassword);
    }

    @BeforeAll
    static void setUp() {
        oracleContainer.start();
    }

    @Test
    public void test_successful_payment_returns_correct_response() {
        Long orderId = 1L;
        BigDecimal totalCost = new BigDecimal("100.00");
        Payment expectedPayment = new Payment();
        String nonce = "fake-valid-nonce";

        expectedPayment.setOrderId(orderId);

        when(paymentRepository.save(any())).thenReturn(expectedPayment);

        Optional<Payment> payment = paymentService.makePayment(orderId, totalCost, nonce);

        assertFalse(payment.isEmpty());
        assertEquals(orderId, payment.get().getOrderId());
    }
}
