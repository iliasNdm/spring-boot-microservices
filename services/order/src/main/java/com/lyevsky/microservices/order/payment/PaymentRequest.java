package com.lyevsky.microservices.order.payment;

import com.lyevsky.microservices.order.customer.CustomerResponse;
import com.lyevsky.microservices.order.order.dao.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Long orderId,
        String orderReference,
        CustomerResponse customer
) {
}
