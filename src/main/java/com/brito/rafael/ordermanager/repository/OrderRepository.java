package com.brito.rafael.ordermanager.repository;

import com.brito.rafael.ordermanager.repository.entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends CrudRepository<OrderEntity, Long> {

    List<OrderEntity> findByClientCode(long clientCode);

    Optional<OrderEntity> findByOrderCode(long orderCode);
}
