package com.flight.booking.flight.mapper;

import com.flight.booking.flight.dto.SeatRequest;
import com.flight.booking.flight.dto.SeatResponse;
import com.flight.booking.flight.model.Seat;
import org.springframework.stereotype.Component;

@Component
public class SeatMapper {
    public SeatResponse mapToDTO(Seat seat) {
        return seat != null ? SeatResponse.builder()
                .seatId(seat.getSeatId())
                .seatNumber(seat.getSeatNumber())
                .type(seat.getType())
                .isBooked(seat.isBooked())
                .build() : null;
    }

    public Seat mapToModel(SeatRequest seatRequest) {
        return Seat.builder()
                .seatId(seatRequest.getSeatId())
                .seatNumber(seatRequest.getSeatNumber())
                .type(seatRequest.getType())
                .build();
    }
}
