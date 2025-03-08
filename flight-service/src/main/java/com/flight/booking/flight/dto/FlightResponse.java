package com.flight.booking.flight.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FlightResponse {
    private int flightId;
    private int flightNumber;
    private String company;
    private int totalSeats;
}
