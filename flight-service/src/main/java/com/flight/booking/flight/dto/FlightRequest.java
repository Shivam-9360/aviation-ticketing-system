package com.flight.booking.flight.dto;

import com.flight.booking.flight.model.SeatType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class FlightRequest {
    private String flightId;
    private int flightNumber;
    private String company;
    private int totalSeats;
    private List<SeatType> seatType;
}
