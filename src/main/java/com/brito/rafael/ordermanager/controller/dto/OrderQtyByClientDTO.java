package com.brito.rafael.ordermanager.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrderQtyByClientDTO {

    @JsonProperty(value = "codigoCliente")
    private final Long clientCode;
    @JsonProperty(value = "quantidadeDePedidosFeitos")
    private final Long orderQuantity;
}
