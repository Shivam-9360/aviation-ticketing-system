package com.flight.booking.schedule.service;

import com.flight.booking.schedule.dto.BookingRequest;
import com.flight.booking.schedule.dto.ScheduleRequest;
import com.flight.booking.schedule.dto.ScheduleResponse;

import java.time.Instant;
import java.util.List;

public interface ScheduleService {
    ScheduleResponse createSchedule(ScheduleRequest schedule);
    List<ScheduleResponse> getAllSchedules();
    List<ScheduleResponse> getSchedulesByDateTime(Instant start, Instant end);
    void deleteScheduleById(String id);
    void deleteAllSchedules();
    ScheduleResponse getScheduleById(String id);
    ScheduleResponse updateSchedule(ScheduleRequest scheduleRequest);
    void deleteByAirportId(int id);
    void deleteByFlightId(String id);
    boolean validateBookingRequest(BookingRequest bookingRequest);
}
