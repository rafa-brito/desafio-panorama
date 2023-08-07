package com.brito.rafael.ordermanager.service;

import com.brito.rafael.ordermanager.consumer.dto.OrderDTO;
import com.brito.rafael.ordermanager.controller.dto.OrderQtyByClientDTO;
import com.brito.rafael.ordermanager.mapper.OrderMapper;
import com.brito.rafael.ordermanager.repository.OrderRepository;
import com.brito.rafael.ordermanager.repository.entity.ItemEntity;
import com.brito.rafael.ordermanager.repository.entity.OrderEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    OrderMapper mapper;
    @Mock
    OrderRepository orderRepository;
    @InjectMocks
    OrderService orderService;

    @Test
    void process() {
        //Arrange
        OrderDTO mockedDTO = new OrderDTO();
        OrderEntity mockedEntity = new OrderEntity();
        mockedEntity.setItems(List.of(new ItemEntity()));
        when(mapper.DTOToEntity(mockedDTO)).thenReturn(mockedEntity);
        //Act
        orderService.process(mockedDTO);
        //Assert
        verify(orderRepository, only()).save(mockedEntity);
    }
}