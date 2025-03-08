package com.flight.booking.flight.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
@Entity
@Table(name = "flight")
public class Flight {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int flightId;

    @Column(unique = true, nullable = false)
    private int flightNumber;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private int totalSeats;
}
