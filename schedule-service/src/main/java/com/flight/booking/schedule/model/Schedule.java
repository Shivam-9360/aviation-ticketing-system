package com.flight.booking.schedule.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "schedule")
public class Schedule {
    @Id
    private String id;
    private String flightId;
    private int sourceAirportId;
    private int destinationAirportId;
    private Instant dateTime;
    private List<Seat> seats;
}