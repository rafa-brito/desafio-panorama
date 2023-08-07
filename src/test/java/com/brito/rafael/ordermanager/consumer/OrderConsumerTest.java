package com.brito.rafael.ordermanager.consumer;

import com.brito.rafael.ordermanager.consumer.dto.OrderDTO;
import com.brito.rafael.ordermanager.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderConsumerTest {

    @Mock
    OrderService orderService;

    @InjectMocks
    OrderConsumer orderConsumer;

    @Test
    void consume() {
        //Arrange
        OrderDTO orderDTO = new OrderDTO();
        //Act
        orderConsumer.consume(orderDTO);
        //Assert
        verify(orderService, only()).process(orderDTO);
    }
}