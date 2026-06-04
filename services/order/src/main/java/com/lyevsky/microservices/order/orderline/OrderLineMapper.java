package com.lyevsky.microservices.order.orderline;

import com.lyevsky.microservices.order.order.dao.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderLineMapper {

    public OrderLine toOrderLine(OrderLineRequest request, Order order) {
        return OrderLine.builder()
                .productId(request.productId())
                .quantity(request.quantity())
                .order(order)
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
