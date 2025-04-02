package com.flight.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class BookingResponse {
    private String id;
    private String scheduleId;
    private int userId;
    private List<Integer> seatNumbers;
    private Integer amount;
    private String status;
    private Instant dateTime;
    private String razorpayPaymentId;
}
