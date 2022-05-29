package com.abdi2148.junit5.mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

// We can extend the test with the @ExtendWith annotation and MockitoExtension.class
@ExtendWith(MockitoExtension.class)
public class MockitoExtensionTest {

    @Mock
    private OrderRepository orderRepository;

    //@InjectMocks
    private OrderService orderService;

    // Now we don't need to use the AutoCloseable and openMocks anymore

    //Using @InjectMocks gets rid of the @BeforeEach method once and for all
    @BeforeEach
    void initService() {
        orderService = new OrderService(orderRepository);
    }

    @Test
    void createOrderSetsTheCreationDate() {
        Order order = new Order();

        when(orderRepository.save(any(Order.class))).then(returnsFirstArg());

        Order savedOrder = orderService.create(order);

        assertNotNull(savedOrder.getCreationDate());
    }
}
