package com.flight.booking.flight.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
@Document(collection = "flight")
public class Flight {
    @Id
    private String flightId;
    private int flightNumber;
    private String company;
    private int totalSeats;
    private List<SeatType> seatType;
}
