package com.brito.rafael.ordermanager.repository.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;


@Data
@Entity
@Table(name = "item")
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String product;
    @Column
    private Long quantity;
    @Column
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;
}
