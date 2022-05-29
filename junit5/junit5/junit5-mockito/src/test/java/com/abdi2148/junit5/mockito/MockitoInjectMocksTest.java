package com.abdi2148.junit5.mockito;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class MockitoInjectMocksTest {

    // We can also tell Mockito to automatically inject mocks to a field, all we have to do is annotate the fields with @InjectMocks
    @Mock
    private OrderRepository orderRepository;
    private AutoCloseable closeable;

    // Now when we call openMocks Mockito will again scan all the fields annotated it with the @Mock annotation and initialise them as mocks


    // Then it will also instantiate the field annotated with @InjectMocks and will try to inject all the mocks to it
    // Again, note that @InjectMocks will only inject mocks created using the @Mock annotation
    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void initService() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }

    @Test
    void createOrderSetsTheCreationDate() {
        Order order = new Order();

        when(orderRepository.save(any(Order.class))).then(returnsFirstArg());

        Order savedOrder = orderService.create(order);

        assertNotNull(savedOrder.getCreationDate());
    }
}
