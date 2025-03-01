package com.flight.booking.flight.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SeatResponse {
    private String seatId;
    private int seatNumber;
    private String type;
    private boolean isBooked;
}
