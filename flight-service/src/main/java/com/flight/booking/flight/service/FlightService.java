package com.flight.booking.flight.service;

import com.flight.booking.flight.dto.FlightRequest;
import com.flight.booking.flight.dto.FlightResponse;
import com.flight.booking.flight.mapper.FlightMapper;
import com.flight.booking.flight.model.Flight;
import com.flight.booking.flight.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlightService {
    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;

    public FlightResponse createFlight(FlightRequest flightRequest) {
        Flight flight = flightMapper.mapToModel(flightRequest);
        flightRepository.save(flight);
        return flightMapper.mapToDTO(flight);
    }

    public FlightResponse getFlightById(String flightId) {
        Flight flight = flightRepository.findById(flightId).orElse(null);
        return flight != null ? flightMapper.mapToDTO(flight) : null;
    }

    public List<FlightResponse> getAllFlights() {
        return flightRepository.findAll().stream().map(flightMapper::mapToDTO).collect(Collectors.toList());
    }

    public FlightResponse updateFlight(FlightRequest flightRequest) {
        Flight flight = flightMapper.mapToModel(flightRequest);
        Flight existingFlight = flightRepository.findById(flight.getFlightId()).orElse(null);

        if(existingFlight == null)
            return null;

        existingFlight.setFlightNumber(flight.getFlightNumber());
        existingFlight.setTotalSeats(flight.getTotalSeats());
        existingFlight.setCompany(flightRequest.getCompany());

        flightRepository.save(existingFlight);

        return flightMapper.mapToDTO(existingFlight);
    }

    public void deleteFlightById(String flightId) {
        flightRepository.deleteById(flightId);
    }

    public void deleteAllFlights() {
        flightRepository.deleteAll();
    }
}
