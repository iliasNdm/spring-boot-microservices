package com.lyevsky.microservices.order.orderline;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderLineRequest(
        Long id,

        @NotNull(message = "Product id is required")
        Long productId,

        @Positive(message = "Quantity must be positive")
        double quantity
) {}