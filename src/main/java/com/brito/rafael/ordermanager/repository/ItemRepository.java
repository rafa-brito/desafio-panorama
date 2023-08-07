package com.brito.rafael.ordermanager.repository;

import com.brito.rafael.ordermanager.repository.entity.ItemEntity;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<ItemEntity, Long> {
}
