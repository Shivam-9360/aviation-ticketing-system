package com.fligh.booking.schedule.repository;

import com.fligh.booking.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule,Integer> {
    void deleteBySourceAirportIdOrDestinationAirportId(int sourceAirportId, int destinationAirportId);
    void deleteByFlightId(String flightId);
}
