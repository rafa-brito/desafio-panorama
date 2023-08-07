package com.brito.rafael.ordermanager.consumer;

import com.brito.rafael.ordermanager.consumer.dto.OrderDTO;
import com.brito.rafael.ordermanager.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderConsumer {

    private final OrderService orderService;

    @RabbitListener(queues = "orderQueue")
    public void consume(OrderDTO orderDTO){
        orderService.process(orderDTO);
    }
}
