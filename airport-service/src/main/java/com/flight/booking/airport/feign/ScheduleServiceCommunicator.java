package com.flight.booking.airport.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "SCHEDULE-SERVICE")
public interface ScheduleServiceCommunicator {

    @DeleteMapping("/api/schedule/airport/{id}")
    public void deleteScheduleById(@PathVariable("id") int id);

}
