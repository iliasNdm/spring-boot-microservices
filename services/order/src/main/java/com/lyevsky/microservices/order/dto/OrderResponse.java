package com.lyevsky.microservices.order.dto;

import com.lyevsky.microservices.order.dao.PaymentMethod;
import com.lyevsky.microservices.order.orderline.OrderLineResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderResponse(
        Long id,
        String reference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        String customerId,
        List<OrderLineResponse> orderLines
) {}

