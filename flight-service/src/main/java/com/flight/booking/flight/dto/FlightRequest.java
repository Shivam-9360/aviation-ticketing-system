package com.flight.booking.flight.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FlightRequest {
    private int flightId;
    private int flightNumber;
    private String company;
    private int totalSeats;
}
