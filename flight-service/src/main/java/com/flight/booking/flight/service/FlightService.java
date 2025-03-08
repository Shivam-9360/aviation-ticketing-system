package com.flight.booking.flight.service;

import com.flight.booking.flight.dto.FlightRequest;
import com.flight.booking.flight.dto.FlightResponse;
import com.flight.booking.flight.model.Flight;

import java.util.List;
import java.util.stream.Collectors;

public interface FlightService {
    public FlightResponse createFlight(FlightRequest flightRequest);
    public FlightResponse getFlightById(int flightId) ;
    public List<FlightResponse> getAllFlights() ;
    public FlightResponse updateFlight(FlightRequest flightRequest);
    public void deleteFlightById(int flightId);
    public void deleteAllFlights();
}
