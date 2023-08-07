package com.brito.rafael.ordermanager.service;

import com.brito.rafael.ordermanager.controller.dto.OrderQtyByClientDTO;
import com.brito.rafael.ordermanager.controller.dto.OrderTotalDTO;
import com.brito.rafael.ordermanager.controller.dto.OrdersByClientDTO;
import com.brito.rafael.ordermanager.repository.entity.OrderEntity;
import com.brito.rafael.ordermanager.exception.OrderManagerException;
import com.brito.rafael.ordermanager.mapper.OrderMapper;
import com.brito.rafael.ordermanager.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public List<OrderQtyByClientDTO> getOrderQtyByClients() {
        List<OrderEntity> allOrders = new ArrayList<>();
        orderRepository.findAll().forEach(allOrders::add);
        Map<Long, Long> ordersQtyByClients = allOrders.stream().collect(Collectors.groupingBy(OrderEntity::getClientCode, Collectors.counting()));
        return buildOrderQtyByClients(ordersQtyByClients);
    }

    private List<OrderQtyByClientDTO> buildOrderQtyByClients(Map<Long, Long> ordersQtyByClients) {
        List<OrderQtyByClientDTO> orderQtyByClientDTOs  = new ArrayList<>();
        ordersQtyByClients.forEach((k, v) -> orderQtyByClientDTOs.add(new OrderQtyByClientDTO(k, v)));
        return orderQtyByClientDTOs;
    }

    public OrderQtyByClientDTO getOrderQtyByClient(Long clientCode) {
        List<OrderEntity> clientOrders = orderRepository.findByClientCode(clientCode);
        return new OrderQtyByClientDTO(clientCode,  Integer.toUnsignedLong(clientOrders.size()));
    }

    public List<OrdersByClientDTO> getOrdersByClients() {
        List<OrderEntity> allOrders = new ArrayList<>();
        orderRepository.findAll().forEach(allOrders::add);
        Map<Long, List<OrderEntity>> ordersByClients = allOrders.stream().collect(Collectors.groupingBy(OrderEntity::getClientCode, Collectors.toList()));
        return buildOrdersByClients(ordersByClients);
    }

    private List<OrdersByClientDTO> buildOrdersByClients(Map<Long, List<OrderEntity>> ordersByClients) {
        List<OrdersByClientDTO> ordersByClientDTOs = new ArrayList<>();
        ordersByClients.forEach((k,v) -> ordersByClientDTOs.add(new OrdersByClientDTO(k, orderMapper.entitiesToDTOs(v))));
        return ordersByClientDTOs;
    }

    public OrdersByClientDTO getOrderByClient(Long clientCode) {
        List<OrderEntity> clientOrders = orderRepository.findByClientCode(clientCode);
        return new OrdersByClientDTO(clientCode, orderMapper.entitiesToDTOs(clientOrders));
    }

    public OrderTotalDTO getOrderTotalValue(Long orderCode) {
        Optional<OrderEntity> optionalOrder = orderRepository.findByOrderCode(orderCode);
        if(optionalOrder.isEmpty())
            throw new OrderManagerException("pedido não encontrado!");
        BigDecimal totalValue = optionalOrder.get().getItems().stream()
                .map(i -> i.getPrice().multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal::add).get();
        return new OrderTotalDTO(orderCode, totalValue);
    }
}
