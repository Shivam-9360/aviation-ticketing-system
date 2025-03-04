package com.flight.booking.airport.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "airport")
@Builder
public class Airport{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100, unique = true)
    private String airportName;

    @Column(nullable = false, length = 100)
    private String city;

    @Column(nullable = false, length = 100)
    private String country;

//    @Column(nullable = false, length = 50, unique = true)
//    private String currencyName;
//
//    @Column(nullable = false, precision = 10, scale = 2)
//    private BigDecimal currencyValue;
}