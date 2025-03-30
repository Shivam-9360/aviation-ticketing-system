package com.flight.booking.schedule.model;

import com.flight.booking.schedule.enums.SeatStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Seat {
    private int seatNumber;
    private SeatStatus status;
}