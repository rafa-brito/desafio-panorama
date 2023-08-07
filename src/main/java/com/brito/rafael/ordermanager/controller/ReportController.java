package com.brito.rafael.ordermanager.controller;

import com.brito.rafael.ordermanager.controller.dto.OrderQtyByClientDTO;
import com.brito.rafael.ordermanager.controller.dto.OrderTotalDTO;
import com.brito.rafael.ordermanager.controller.dto.OrdersByClientDTO;
import com.brito.rafael.ordermanager.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("order-quantity-by-clients/")
    public List<OrderQtyByClientDTO> getOrdersQtyByClients(){
        return reportService.getOrderQtyByClients();
    }

    @GetMapping("order-quantity-by-clients/{clientCode}")
    public OrderQtyByClientDTO getOrdersQtyByClient(@PathVariable Long clientCode){
        return reportService.getOrderQtyByClient(clientCode);
    }

    @GetMapping("order-by-clients/")
    public List<OrdersByClientDTO> getOrdersByClients(){
        return reportService.getOrdersByClients();
    }

    @GetMapping("order-by-clients/{clientCode}")
    public OrdersByClientDTO getOrdersByClient(@PathVariable Long clientCode){
        return reportService.getOrderByClient(clientCode);
    }

    @GetMapping("order-total-value/{orderCode}")
    public OrderTotalDTO getOrderTotalValue(@PathVariable Long orderCode){
        return reportService.getOrderTotalValue(orderCode);
    }
}
