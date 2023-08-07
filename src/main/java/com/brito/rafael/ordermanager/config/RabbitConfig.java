package com.brito.rafael.ordermanager.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue orderQueue() {
        return QueueBuilder.durable("orderQueue").build();
    }

    @Bean
    public Exchange orderExchange() {
        return ExchangeBuilder.topicExchange("orderExchange").build();
    }

    @Bean
    public Binding orderBinding(Queue orderQueue, Exchange orderExchange){
        return BindingBuilder.bind(orderQueue).to(orderExchange).with("*").noargs();
    }

    @Bean
    public RabbitAdmin amqpAdmin(ConnectionFactory factory) {
        return new RabbitAdmin(factory);
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
