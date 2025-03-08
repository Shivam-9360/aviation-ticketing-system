package com.fligh.booking.schedule.dto;

import com.fligh.booking.schedule.model.Seat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScheduleResponse {
    LocalDateTime dateTime;
    FlightResponse flightResponse;
    AirportResponse sourceAirportResponse;
    AirportResponse destinationAirportResponse;
    List<Seat> seats;
}
