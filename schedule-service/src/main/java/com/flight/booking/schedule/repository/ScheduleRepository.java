package com.flight.booking.schedule.repository;

import com.flight.booking.schedule.model.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScheduleRepository extends MongoRepository<Schedule, String> {
    void deleteBySourceAirportIdOrDestinationAirportId(int sourceAirportId, int destinationAirportId);
    void deleteByFlightId(String flightId);
    @Query("{ 'dateTime': { $lt: ?0 } }")
    void cleanSchedules(Instant dateTime);
    @Query("{ 'dateTime': { $gte: ?0, $lte: ?1 } }")
    List<Schedule> findSchedulesBetween(Instant start, Instant end);
}