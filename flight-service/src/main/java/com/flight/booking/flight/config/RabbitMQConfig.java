package com.flight.booking.flight.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String FLIGHT_DELETE_QUEUE = "flight.delete.queue";
    public static final String FLIGHT_EXCHANGE = "flight.exchange";
    public static final String FLIGHT_DELETE_ROUTING_KEY = "flight.delete.routingKey";

    @Bean
    public Queue flightDeleteQueue(){
        return new Queue(FLIGHT_DELETE_QUEUE,true);
    }

    @Bean
    public DirectExchange flightExchange(){
        return new DirectExchange(FLIGHT_EXCHANGE);
    }

    @Bean
    public Binding bindingFlightExchangeQueue(Queue airportDeleteQueue, DirectExchange airportExchange){
        return BindingBuilder.bind(airportDeleteQueue).to(airportExchange).with(FLIGHT_DELETE_ROUTING_KEY);
    }

}
