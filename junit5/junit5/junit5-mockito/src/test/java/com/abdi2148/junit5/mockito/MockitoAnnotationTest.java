package com.abdi2148.junit5.mockito;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class MockitoAnnotationTest {

    // If we don't want to call the mock methods manually on each dependency to be mocked, we can use the @Mock annotation on the dependencies

    // We tell Mockito to scan all the dependencies with the @Mock annotations and let it do the initialisation
    @Mock
    private OrderRepository orderRepository;
    private AutoCloseable closeable;
    private OrderService orderService;

    // We can do this by calling the Mockito annotations as openMocks methods with this as argument
    @BeforeEach
    void initService() {
        closeable = MockitoAnnotations.openMocks(this);
        orderService = new OrderService(orderRepository);
    }

    // This will return an AutoCloseable which can then be used to reset the mocks after the test
    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }

    // Mockito will scan all the fields in this class that are annotated with the @Mock annotation and initialise them as mocks
    @Test
    void createOrderSetsTheCreationDate() {
        when(orderRepository.save(any(Order.class))).then(returnsFirstArg());

        Order order = new Order();

        Order savedOrder = orderService.create(order);

        assertNotNull(savedOrder.getCreationDate());
    }
}
