package com.brito.rafael.ordermanager.repository.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "client_order")
public class OrderEntity {

    @Id
    private Long orderCode;
    @Column
    private Long clientCode;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<ItemEntity> items;
}
