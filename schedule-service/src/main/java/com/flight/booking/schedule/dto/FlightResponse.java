package com.flight.booking.schedule.dto;

import com.flight.booking.schedule.model.SeatType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlightResponse {
    private String flightId;
    private int flightNumber;
    private String company;
    private int totalSeats;
    private List<SeatType> seatType;
}
