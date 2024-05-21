package com.pp.paymentservice.service;

import com.braintreegateway.*;
import com.pp.paymentservice.exception.PaymentTransactionException;
import com.pp.paymentservice.model.Payment;
import com.pp.paymentservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {

    private final BraintreeGateway brainTreeGateway;
    private final PaymentRepository paymentRepository;

    public Map<String, String> getClientToken() {
        ClientTokenRequest clientTokenRequest = new ClientTokenRequest();
        String clientToken = brainTreeGateway.clientToken().generate(clientTokenRequest);
        Map<String, String> map = new HashMap<>();
        map.put("clientToken", clientToken);
        return map;
    }

    public Optional<Payment> makePayment(Long orderId, BigDecimal totalCost, String paymentMethodNonce) {
        String transactionId = this.processPayment(totalCost, paymentMethodNonce);
        if (transactionId != null) {
            return Optional.of(insertPayment(orderId, transactionId));
        }
        return Optional.empty();
    }

    private Payment insertPayment(Long orderId, String transactionId) {
        Payment payment = new Payment();
        payment.setDateCreated(LocalDateTime.now());
        payment.setTransactionId(transactionId);
        payment.setOrderId(orderId);
        return paymentRepository.save(payment);
    }

    private String processPayment(BigDecimal totalCost, String paymentMethodNonce) {
        Result<Transaction> result = processTransaction(totalCost, paymentMethodNonce);

        if (result.isSuccess()) {
            Transaction transaction = result.getTarget();
            System.out.println("Success!: " + transaction.getId());
            return transaction.getId();
        } else if (result.getTransaction() != null) {
            Transaction transaction = result.getTransaction();
            throw new PaymentTransactionException(transaction);
        } else {
            for (ValidationError error : result.getErrors().getAllDeepValidationErrors()) {
                System.out.println("Attribute: " + error.getAttribute());
                System.out.println("  Code: " + error.getCode());
                System.out.println("  Message: " + error.getMessage());
            }
            return null;
        }
    }

    public Result<Transaction> processTransaction(BigDecimal totalCost, String paymentMethodNonce) {
        TransactionRequest req = new TransactionRequest().amount(totalCost).paymentMethodNonce(paymentMethodNonce)
                .options().submitForSettlement(true).done();

        Result<Transaction> result = brainTreeGateway.transaction().sale(req);
        System.out.println(result.getMessage());
        return result;
    }
}


