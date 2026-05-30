package com.lyevsky.microservices.order.service;


import com.lyevsky.microservices.order.customer.CustomerClient;
import com.lyevsky.microservices.order.dao.OrderRepository;
import com.lyevsky.microservices.order.dto.OrderRequest;
import com.lyevsky.microservices.order.dto.OrderResponse;
import com.lyevsky.microservices.order.exceptions.BusinessException;
import com.lyevsky.microservices.order.mappers.OrderMapper;
import com.lyevsky.microservices.order.orderline.OrderLineMapper;
import com.lyevsky.microservices.order.orderline.OrderLineRepository;
import com.lyevsky.microservices.order.product.ProductClient;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper orderLineMapper;

    public Long createOrder(@Valid OrderRequest request) {

//        check the customer existance  => via open feign
        customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Customer with id " + request.customerId() + " not found"));

//        purshase the products => check the products existance and availability using RestTemplate
        productClient.purchaseProducts(request.products());

//        persist the order
        var order = orderRepository.save(orderMapper.toOrder(request));

//        persist the order lines
        request.products().stream()
                .map(lineRequest -> orderLineMapper.toOrderLine(lineRequest, order))
                .forEach(orderLineRepository::save);

//        start the payment process
//        send the order confirmation to our kafka broker
        return order.getId();
    }

    public OrderResponse findById(Integer orderId) {
        return null;
    }
    public List<OrderResponse> findAll() {
        return null;
    }
}
