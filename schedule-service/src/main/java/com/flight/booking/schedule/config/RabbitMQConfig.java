package com.flight.booking.schedule.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String AIRPORT_DELETE_QUEUE = "airport.delete.queue";
    public static final String FLIGHT_DELETE_QUEUE = "flight.delete.queue";

    @Bean
    public Queue airportDeleteQueue() {
        return new Queue(AIRPORT_DELETE_QUEUE, true);
    }

    @Bean
    public Queue flightDeleteQueue() {
        return new Queue(FLIGHT_DELETE_QUEUE, true);
    }
}
