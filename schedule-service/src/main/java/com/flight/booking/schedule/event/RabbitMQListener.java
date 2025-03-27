package com.flight.booking.schedule.event;

import com.flight.booking.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RabbitMQListener {

    private final ScheduleService scheduleService;

    @RabbitListener(queues = "airport.delete.queue")
    public void handleAirportDeleteEvent(Integer airportId) {
        System.out.println("Received Airport Delete Event for ID: " + airportId);
        scheduleService.deleteByAirportId(airportId);
    }
    @RabbitListener(queues = "flight.delete.queue")
    public void handleFlightDeleteEvent(String flightId){
        System.out.println("Received Airport Delete Event for ID: " + flightId);
        scheduleService.deleteByFlightId(flightId);
    }
}