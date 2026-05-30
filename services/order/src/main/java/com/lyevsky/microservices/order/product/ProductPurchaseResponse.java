package com.lyevsky.microservices.order.product;

import java.math.BigDecimal;

public record ProductPurchaseResponse(
        Long productId,
        String name,
        String description,
        double quantity,
        BigDecimal price
) {}