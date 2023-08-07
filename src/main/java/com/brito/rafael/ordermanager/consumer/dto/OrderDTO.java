package com.brito.rafael.ordermanager.consumer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {

    @JsonProperty("codigoPedido")
    private Long orderCode;
    @JsonProperty("codigoCliente")
    private Long clientCode;
    @JsonProperty("itens")
    private List<ItemDTO> items;
}
