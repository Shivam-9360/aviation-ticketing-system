package com.flight.booking.schedule.dto;

import com.flight.booking.schedule.model.Seat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScheduleResponse {
    String id;
    String dateTime;;
    FlightResponse flightResponse;
    AirportResponse sourceAirportResponse;
    AirportResponse destinationAirportResponse;
    List<Seat> seats;
}
