package com.abdi2148.junit5.mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MockitoManualTest {

    // Initialises variables before each test
    private OrderRepository orderRepository;
    private OrderService orderService;


    // We want the order service to call a mocked version of the order repo, by using a static mock method and passes the repo class as an argument
    @BeforeEach
    void initService() {
        orderRepository = mock(OrderRepository.class);
        // then construct the order service and pass the mocked instance repo to it
        orderService = new OrderService(orderRepository);
    }

    //
    @Test
    void createOrderSetsTheCreationDate() {
        Order order = new Order();
        // Whenever the save method of the order repo mock is called return something, in this case return the first argument
        when(orderRepository.save(any(Order.class))).then(returnsFirstArg());

        // When we now tell the order service to create an order it will call order repo mock behind the scenes
        Order savedOrder = orderService.create(order);

        assertNotNull(savedOrder.getCreationDate());
    }
}
