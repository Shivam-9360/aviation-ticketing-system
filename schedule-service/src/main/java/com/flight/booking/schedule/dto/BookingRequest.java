package com.flight.booking.schedule.dto;

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
    String scheduleId;
    List<Integer> seatNumbers;
}
