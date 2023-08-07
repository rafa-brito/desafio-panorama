package com.brito.rafael.ordermanager.mapper;

import com.brito.rafael.ordermanager.consumer.dto.OrderDTO;
import com.brito.rafael.ordermanager.repository.entity.OrderEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {

    OrderEntity DTOToEntity(OrderDTO orderDTO);

    List<OrderDTO> entitiesToDTOs(List<OrderEntity> orderEntities);


}
