package com.lyevsky.microservices.product.dto;

import java.math.BigDecimal;

public record ProductPurshaseResponse(
        Long productId,
        String name,
        String description,
        double quantity,
        BigDecimal price
) {
}