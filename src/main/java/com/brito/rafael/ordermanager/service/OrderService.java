package com.brito.rafael.ordermanager.service;


import com.brito.rafael.ordermanager.consumer.dto.OrderDTO;
import com.brito.rafael.ordermanager.repository.entity.OrderEntity;
import com.brito.rafael.ordermanager.mapper.OrderMapper;
import com.brito.rafael.ordermanager.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;


@Log4j2
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper mapper;
    private final OrderRepository orderRepository;

    public void process(OrderDTO orderDTO) {
        OrderEntity orderEntity = mapper.DTOToEntity(orderDTO);
        orderEntity.getItems().forEach(i -> i.setOrder(orderEntity));
        orderRepository.save(orderEntity);
        log.info("pedido persistido com sucesso!");
    }
}
