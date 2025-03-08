package com.fligh.booking.schedule.feign;

import com.fligh.booking.schedule.dto.DTO;
import com.fligh.booking.schedule.dto.FlightResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "FLIGHT-SERVICE")
public interface FlightServiceCommunicator {

    @GetMapping("/api/flight/{id}")
    public DTO<FlightResponse> getFlightById(@PathVariable("id") int id);
}
