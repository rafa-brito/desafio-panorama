package com.brito.rafael.ordermanager.service;

import com.brito.rafael.ordermanager.consumer.dto.OrderDTO;
import com.brito.rafael.ordermanager.controller.dto.OrderQtyByClientDTO;
import com.brito.rafael.ordermanager.controller.dto.OrderTotalDTO;
import com.brito.rafael.ordermanager.controller.dto.OrdersByClientDTO;
import com.brito.rafael.ordermanager.mapper.OrderMapper;
import com.brito.rafael.ordermanager.repository.OrderRepository;
import com.brito.rafael.ordermanager.repository.entity.ItemEntity;
import com.brito.rafael.ordermanager.repository.entity.OrderEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReportServiceTest {

    @Mock
    OrderRepository orderRepository;
    @Mock
    OrderMapper orderMapper;
    @InjectMocks
    ReportService reportService;

    @Test
    void getOrderQtyByClients() {
        //Arrange
        List<OrderEntity> mockedOrderEntities = buildMultiMockedOrderEntities();
        long expectedValue = mockedOrderEntities.stream().map(OrderEntity::getClientCode).distinct().count();
        when(orderRepository.findAll()).thenReturn(mockedOrderEntities);
        //Act
        List<OrdersByClientDTO> actualValue = reportService.getOrdersByClients();
        //Assert
        assertEquals(expectedValue, actualValue.size());
    }

    @Test
    void getOrderQtyByClient() {
        //Arrange
        List<OrderEntity> mockedOrderEntities = buildMultiMockedOrderEntities();
        when(orderRepository.findByClientCode(1L)).thenReturn(mockedOrderEntities);
        //Act
        OrderQtyByClientDTO actualValue = reportService.getOrderQtyByClient(1L);
        //Assert
        assertEquals(mockedOrderEntities.size(), actualValue.getOrderQuantity());
    }

    @Test
    void getOrdersByClients() {
        //Arrange
        List<OrderEntity> mockedOrderEntities = buildMultiMockedOrderEntities();
        List<OrderDTO> expectedValue =  List.of(new OrderDTO());
        when(orderRepository.findAll()).thenReturn(mockedOrderEntities);
        when(orderMapper.entitiesToDTOs(any())).thenReturn(expectedValue);
        //Act
        List<OrdersByClientDTO> actualValue = reportService.getOrdersByClients();
        //Assert
        assertEquals(expectedValue, actualValue.get(0).getOrders());
    }

    @Test
    void getOrderByClient() {
        //Arrange
        List<OrderEntity> mockedOrderEntities = buildSingleMockedOrderEntities();
        List<OrderDTO> expectedValue =  List.of(new OrderDTO());
        when(orderRepository.findByClientCode(1L)).thenReturn(mockedOrderEntities);
        when(orderMapper.entitiesToDTOs(mockedOrderEntities)).thenReturn(expectedValue);
        //Act
        OrdersByClientDTO actualValue = reportService.getOrderByClient(1L);
        //Assert
        assertEquals(expectedValue, actualValue.getOrders());
    }

    @Test
    void getOrderTotalValue() {
        //Arrange
        OrderEntity mockedOrder = new OrderEntity();
        ItemEntity mockedItem = new ItemEntity();
        mockedItem.setPrice(BigDecimal.valueOf(200L));
        mockedItem.setQuantity(2L);
        mockedOrder.setItems(List.of(mockedItem));
        Optional<OrderEntity> mokedOptionalOrder = Optional.of(mockedOrder);
        when(orderRepository.findByOrderCode(1L)).thenReturn(mokedOptionalOrder);
        //Act
        OrderTotalDTO actualValue = reportService.getOrderTotalValue(1L);
        //Assert
        assertEquals(BigDecimal.valueOf(400L), actualValue.getTotal());
        assertEquals(1L, actualValue.getClientCode());
    }

    private List<OrderEntity> buildMultiMockedOrderEntities(){
        OrderEntity firstOrderEntity = new OrderEntity();
        firstOrderEntity.setClientCode(1L);
        OrderEntity secondOrderEntity = new OrderEntity();
        secondOrderEntity.setClientCode(1L);
        OrderEntity thirdOrderEntity = new OrderEntity();
        thirdOrderEntity.setClientCode(2L);
        return List.of(firstOrderEntity, secondOrderEntity, thirdOrderEntity);
    }

    private List<OrderEntity> buildSingleMockedOrderEntities(){
        OrderEntity firstOrderEntity = new OrderEntity();
        firstOrderEntity.setClientCode(1L);
        OrderEntity secondOrderEntity = new OrderEntity();
        secondOrderEntity.setClientCode(1L);
        OrderEntity thirdOrderEntity = new OrderEntity();
        thirdOrderEntity.setClientCode(1L);
        return List.of(firstOrderEntity, secondOrderEntity, thirdOrderEntity);
    }
}