package com.brito.rafael.ordermanager.controller.dto;

import com.brito.rafael.ordermanager.consumer.dto.OrderDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class OrdersByClientDTO {

    @JsonProperty(value = "codigoCliente")
    private final long clientCode;
    @JsonProperty(value = "pedidos")
    private final List<OrderDTO> orders;
}
