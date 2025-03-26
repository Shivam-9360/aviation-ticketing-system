package com.flight.booking.airport.event;

import com.flight.booking.airport.config.RabbitMQConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AirportEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public void sendAirportDeletedEvent(int airportId) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.AIRPORT_EXCHANGE, RabbitMQConfig.AIRPORT_DELETE_ROUTING_KEY, airportId);
        System.out.println("Sent airport delete event for ID: " + airportId);
    }
}