package com.lyevsky.microservices.order.order.mappers;

import com.lyevsky.microservices.order.order.dao.Order;
import com.lyevsky.microservices.order.orderline.OrderLine;
import com.lyevsky.microservices.order.orderline.OrderLineRequest;
import com.lyevsky.microservices.order.orderline.OrderLineResponse;
import com.lyevsky.microservices.order.order.dto.OrderRequest;
import com.lyevsky.microservices.order.order.dto.OrderResponse;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class OrderMapper {

    public Order toOrder(OrderRequest request) {
        if (request == null) return null;
        return Order.builder()
                .reference(request.reference())
                .totalAmount(request.amount())
                .paymentMethod(request.paymentMethod())
                .customerId(request.customerId())
                .build();
    }

    public OrderResponse fromOrder(Order order) {
        List<OrderLineResponse> lines = order.getOrderLines() == null
                ? Collections.emptyList()
                : order.getOrderLines().stream().map(this::fromOrderLine).toList();

        return new OrderResponse(
                order.getId(),
                order.getReference(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                order.getCustomerId(),
                lines
        );
    }

    public OrderLine toOrderLine(OrderLineRequest request) {
        if (request == null) return null;
        return OrderLine.builder()
                .productId(request.productId())
                .quantity(request.quantity())
                .build();
    }

    public OrderLineResponse fromOrderLine(OrderLine orderLine) {
        return new OrderLineResponse(
                orderLine.getId(),
                orderLine.getProductId(),
                orderLine.getQuantity()
        );
    }
}