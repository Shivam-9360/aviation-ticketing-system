package com.flight.booking.schedule.feign;

import com.flight.booking.schedule.dto.DTO;
import com.flight.booking.schedule.dto.FlightResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "FLIGHT-SERVICE", url = "${flight.service.url:}")
public interface FlightServiceCommunicator {

    @GetMapping("/api/flight/{id}")
    public DTO<FlightResponse> getFlightById(@PathVariable("id") String id);
}
