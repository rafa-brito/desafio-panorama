package com.brito.rafael.ordermanager.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderTotalDTO {

    @JsonProperty(value = "codigoCliente")
    private final long clientCode;
    @JsonProperty(value = "valorTotalDoPedido")
    private final BigDecimal total;
}
