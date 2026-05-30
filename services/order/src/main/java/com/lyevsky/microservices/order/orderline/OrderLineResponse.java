package com.lyevsky.microservices.order.orderline;

public record OrderLineResponse(
        Long id,
        Long productId,
        double quantity
) {}