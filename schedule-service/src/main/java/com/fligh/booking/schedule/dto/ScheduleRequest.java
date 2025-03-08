package com.fligh.booking.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScheduleRequest {
    int flightId;
    int sourceAirport;
    int destinationAirport;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime;
}
