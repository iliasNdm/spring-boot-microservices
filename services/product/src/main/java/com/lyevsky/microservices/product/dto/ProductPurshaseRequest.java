package com.lyevsky.microservices.product.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductPurshaseRequest(
        @NotNull(message = "Product id is required")
        Long productId,
        @Positive(message = "Quantity must be positive")
        double quantity
) {
}