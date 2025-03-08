package com.fligh.booking.schedule.dto;

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
public class FlightResponse {
    private int flightId;
    private int flightNumber;
    private String company;
    private int totalSeats;
    private int bookedSeats;
}
