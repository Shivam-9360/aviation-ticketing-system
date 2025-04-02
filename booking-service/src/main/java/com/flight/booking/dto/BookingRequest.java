package com.flight.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class BookingRequest {
    private String scheduleId;
    private int userId;
    private List<Integer> seatNumbers;
    private Integer amount;
}
