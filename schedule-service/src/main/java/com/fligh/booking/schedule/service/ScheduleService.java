package com.fligh.booking.schedule.service;

import com.fligh.booking.schedule.dto.ScheduleRequest;
import com.fligh.booking.schedule.dto.ScheduleResponse;

import java.util.List;

public interface ScheduleService {
    ScheduleResponse createSchedule(ScheduleRequest schedule);
    List<ScheduleResponse> getAllSchedules();
    void deleteScheduleById(String id);
    void deleteAllSchedules();
    ScheduleResponse getScheduleById(String id);
    ScheduleResponse updateSchedule(ScheduleRequest scheduleRequest);
    void deleteByAirportId(int id);
    void deleteByFlightId(String id);
}
