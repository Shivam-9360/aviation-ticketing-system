package com.flight.booking.flight.event;


import com.flight.booking.flight.config.RabbitMQConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlightEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public void sendFlightDeletedEvent(String flightId) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.FLIGHT_EXCHANGE, RabbitMQConfig.FLIGHT_DELETE_ROUTING_KEY, flightId);
        System.out.println("Sent flight delete event for ID: " + flightId);
    }
}