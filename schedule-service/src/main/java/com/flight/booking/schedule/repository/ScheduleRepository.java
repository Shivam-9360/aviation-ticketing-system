package com.flight.booking.schedule.repository;

import com.flight.booking.schedule.model.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ScheduleRepository extends MongoRepository<Schedule, String> {
    void deleteBySourceAirportIdOrDestinationAirportId(int sourceAirportId, int destinationAirportId);
    void deleteByFlightId(String flightId);
}