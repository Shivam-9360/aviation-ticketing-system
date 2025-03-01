package com.flight.booking.flight.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "seat")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Seat {
    @Id
    private String seatId;
    private int seatNumber;
    private String type;
    private boolean isBooked;
}