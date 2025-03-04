package com.flight.booking.airport.service;

import com.flight.booking.airport.dto.AirportRequest;
import com.flight.booking.airport.dto.AirportResponse;
import com.flight.booking.airport.entity.Airport;

import java.util.List;

public interface AirportService {
    List<AirportResponse> getAllAirports();
    AirportResponse createAirport(AirportRequest airport);
    void deleteAirportById(int id);
    AirportResponse getAirportById(int id);
    AirportResponse updateAirport(AirportRequest airport);
}