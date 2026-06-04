package com.lyevsky.microservices.order.order.service;


import com.lyevsky.microservices.order.customer.CustomerClient;
import com.lyevsky.microservices.order.kafka.OrderConfirmation;
import com.lyevsky.microservices.order.kafka.OrderProducer;
import com.lyevsky.microservices.order.order.dao.OrderRepository;
import com.lyevsky.microservices.order.order.dto.OrderRequest;
import com.lyevsky.microservices.order.order.dto.OrderResponse;
import com.lyevsky.microservices.order.exceptions.BusinessException;
import com.lyevsky.microservices.order.order.mappers.OrderMapper;
import com.lyevsky.microservices.order.orderline.OrderLineMapper;
import com.lyevsky.microservices.order.orderline.OrderLineRepository;
import com.lyevsky.microservices.order.product.ProductClient;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper orderLineMapper;
    private final OrderProducer orderProducer;

    public Long createOrder(@Valid OrderRequest request) {

//        check the customer existance  => via open feign
        var customer = customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Customer with id " + request.customerId() + " not found"));

//        purshase the products => check the products existance and availability using RestTemplate
        var purchasedProducts = productClient.purchaseProducts(request.products());

//        persist the order
        var order = orderRepository.save(orderMapper.toOrder(request));

//        persist the order lines
        request.products().stream()
                .map(lineRequest -> orderLineMapper.toOrderLine(lineRequest, order))
                .forEach(orderLineRepository::save);

//        todo start the payment process

//        send the order confirmation to our kafka broker
        orderProducer.sendOrderConfirnmation(new OrderConfirmation(
                request.reference(),
                request.amount(),
                request.paymentMethod(),
                customer,
                purchasedProducts
        ));

        return order.getId();
    }

    public OrderResponse findById(Long orderId) {
        return
                orderRepository.findById(orderId)
                .map(orderMapper::fromOrder)
                        .orElseThrow(() -> new EntityNotFoundException("Order with id " + orderId + " not found"));
    }
    public List<OrderResponse> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::fromOrder)
                .collect(Collectors.toList());
    }
}
