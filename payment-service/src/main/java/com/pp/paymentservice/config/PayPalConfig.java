package com.pp.paymentservice.config;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Environment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PayPalConfig {
    @Bean
    public BraintreeGateway getPaypalClient(
            @Value("${braintree.merchant-id}") String merchantId,
            @Value("${braintree.public-key}") String publicKey,
            @Value("${braintree.private-key}") String privateKey) {
        return new  BraintreeGateway(
                Environment.SANDBOX, merchantId, publicKey, privateKey
        );
    }
}
