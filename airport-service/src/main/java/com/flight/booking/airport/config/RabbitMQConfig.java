package com.flight.booking.airport.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String AIRPORT_DELETE_QUEUE = "airport.delete.queue";
    public static final String AIRPORT_EXCHANGE = "airport.exchange";
    public static final String AIRPORT_DELETE_ROUTING_KEY = "airport.delete.routingKey";

    @Bean
    public Queue airportDeleteQueue(){
        return new Queue(AIRPORT_DELETE_QUEUE,true);
    }

    @Bean
    public DirectExchange airportExchange(){
        return new DirectExchange(AIRPORT_EXCHANGE);
    }

    @Bean
    public Binding bindingAirportDeleteQueue(Queue airportDeleteQueue, DirectExchange airportExchange){
        return BindingBuilder.bind(airportDeleteQueue).to(airportExchange).with(AIRPORT_DELETE_ROUTING_KEY);
    }

}
