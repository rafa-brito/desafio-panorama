package com.brito.rafael.ordermanager.consumer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemDTO {

    @JsonProperty("produto")
    private String product;
    @JsonProperty("quantidade")
    private Long quantity;
    @JsonProperty("preco")
    private BigDecimal price;
}
