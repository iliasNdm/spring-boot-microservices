package com.lyevsky.microservices.order.kafka;

import com.lyevsky.microservices.order.customer.CustomerResponse;
import com.lyevsky.microservices.order.order.dao.PaymentMethod;
import com.lyevsky.microservices.order.product.ProductPurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totoalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<ProductPurchaseResponse> orderLines

) {
}
