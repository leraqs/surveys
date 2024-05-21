package com.pp.paymentservice.exception;

import com.braintreegateway.Transaction;

import static java.lang.String.format;

public class PaymentTransactionException extends RuntimeException {
    public PaymentTransactionException(Transaction transaction) {
        super(format("""
                        Failed!: %s. Error processing transaction:
                        Status: %s
                        Code: %s
                        Text: %s
                        """,
                transaction.getId(),
                transaction.getStatus(),
                transaction.getProcessorResponseCode(),
                transaction.getProcessorResponseText()));
    }
}
