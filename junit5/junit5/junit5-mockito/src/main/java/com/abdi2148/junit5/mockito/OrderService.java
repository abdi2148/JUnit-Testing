package com.abdi2148.junit5.mockito;

import java.time.LocalDateTime;

public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order create(Order order) {
        order.setCreationDate(LocalDateTime.now());
        return orderRepository.save(order);
    }
}

// Order service lets the user create new orders, once an order is created it will be assigned a creation date, the service class will ask the repo to save it