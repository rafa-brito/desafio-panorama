package com.brito.rafael.ordermanager.controller;

import com.brito.rafael.ordermanager.consumer.dto.OrderDTO;
import com.brito.rafael.ordermanager.controller.dto.OrderQtyByClientDTO;
import com.brito.rafael.ordermanager.controller.dto.OrderTotalDTO;
import com.brito.rafael.ordermanager.controller.dto.OrdersByClientDTO;
import com.brito.rafael.ordermanager.service.ReportService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReportControllerTest {

    @Mock
    ReportService reportService;
    @InjectMocks
    ReportController reportController;

    @Test
    void getOrdersQtyByClients() {
        //Arrange
        List<OrderQtyByClientDTO> expectedValue = List.of(new OrderQtyByClientDTO(1L, 1L));
        when(reportService.getOrderQtyByClients()).thenReturn(expectedValue);
        //Act
        List<OrderQtyByClientDTO> actualValue = reportController.getOrdersQtyByClients();
        //Assert
        verify(reportService, only()).getOrderQtyByClients();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void getOrdersQtyByClient() {
        //Arrange
        OrderQtyByClientDTO expectedValue = new OrderQtyByClientDTO(1L, 1L);
        when(reportService.getOrderQtyByClient(1L)).thenReturn(expectedValue);
        //Act
        OrderQtyByClientDTO actualValue = reportController.getOrdersQtyByClient(1L);
        //Assert
        verify(reportService, only()).getOrderQtyByClient(1L);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void getOrdersByClients() {
        //Arrange
        List<OrdersByClientDTO> expectedValue = List.of(new OrdersByClientDTO(1L, List.of(new OrderDTO())));
        when(reportService.getOrdersByClients()).thenReturn(expectedValue);
        //Act
        List<OrdersByClientDTO> actualValue = reportController.getOrdersByClients();
        //Assert
        verify(reportService, only()).getOrdersByClients();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void getOrdersByClient() {
        //Arrange
        OrdersByClientDTO expectedValue = new OrdersByClientDTO(1L, List.of(new OrderDTO()));
        when(reportService.getOrderByClient(1L)).thenReturn(expectedValue);
        //Act
        OrdersByClientDTO actualValue = reportController.getOrdersByClient(1L);
        //Assert
        verify(reportService, only()).getOrderByClient(1L);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void getOrderTotalValue() {
        //Arrange
        OrderTotalDTO expectedValue = new OrderTotalDTO(1L, BigDecimal.ONE);
        when(reportService.getOrderTotalValue(1L)).thenReturn(expectedValue);
        //Act
        OrderTotalDTO actualValue = reportController.getOrderTotalValue(1L);
        //Assert
        verify(reportService, only()).getOrderTotalValue(1L);
        assertEquals(expectedValue, actualValue);
    }
}