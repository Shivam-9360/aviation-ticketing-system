package com.flight.booking.flight.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "SCHEDULE-SERVICE")
public interface ScheduleServiceCommunicator {

    @DeleteMapping("/api/schedule/flight/{id}")
    void deleteScheduleByFlightId(@PathVariable("id") String id);

    @DeleteMapping("/api/schedules")
    void deleteAllSchedules();
}
