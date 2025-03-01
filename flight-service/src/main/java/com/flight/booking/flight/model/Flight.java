package com.flight.booking.flight.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "flight")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Flight {
    @Id
    private String flightId;
    private int flightNumber;
    private String company;
    private int totalSeats;
    @DBRef(lazy = true)
    private List<Seat> seats;
}
