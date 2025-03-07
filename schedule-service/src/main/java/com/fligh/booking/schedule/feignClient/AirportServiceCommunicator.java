package com.fligh.booking.schedule.feignClient;

import com.fligh.booking.schedule.dto.AirportResponse;
import com.fligh.booking.schedule.dto.DTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "AIRPORT-SERVICE")
public interface AirportServiceCommunicator {
    @GetMapping("/api/airport/{id}")
    public DTO<AirportResponse> getAirportById(@PathVariable("id") int id);
}
