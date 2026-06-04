package com.lyevsky.microservices.order.order.dto;

import com.lyevsky.microservices.order.order.dao.PaymentMethod;
import com.lyevsky.microservices.order.orderline.OrderLineRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        String id,

        String reference,

        @Positive(message = "Total amount must be positive")
        BigDecimal amount,

        @NotNull(message = "Payment method is required")
        PaymentMethod paymentMethod,

        @NotBlank(message = "Customer id is required")
        String customerId,

        @NotEmpty(message = "Order must contain at least one product")
        @Valid
        List<OrderLineRequest> products
) {}

