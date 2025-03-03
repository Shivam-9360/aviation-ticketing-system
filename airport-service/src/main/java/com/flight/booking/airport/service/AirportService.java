package com.flight.booking.airport.service;

import com.flight.booking.airport.entity.Airport;

import java.util.List;

public interface AirportService {
    List<Airport> getAllAirports();
    Airport createAirport(Airport airport);
    void deleteAirportById(int id);
    Airport getAirportById(int id);
    Airport updateAirport(Airport airport);
}