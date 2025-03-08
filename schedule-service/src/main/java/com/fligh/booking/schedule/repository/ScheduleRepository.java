package com.fligh.booking.schedule.repository;

import com.fligh.booking.schedule.model.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ScheduleRepository extends MongoRepository<Schedule, String> {
    void deleteBySourceAirportIdOrDestinationAirportId(int sourceAirportId, int destinationAirportId);
    void deleteByFlightId(int flightId);
}