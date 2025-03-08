package com.flight.booking.flight.service;

import com.flight.booking.flight.dto.FlightRequest;
import com.flight.booking.flight.dto.FlightResponse;
import com.flight.booking.flight.exception.FlightNotFoundException;
import com.flight.booking.flight.feign.ScheduleServiceCommunicator;
import com.flight.booking.flight.mapper.FlightMapper;
import com.flight.booking.flight.model.Flight;
import com.flight.booking.flight.model.Seat;
import com.flight.booking.flight.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {
    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;

    @Autowired
    private ScheduleServiceCommunicator scheduleServiceCommunicator;

    public FlightResponse createFlight(FlightRequest flightRequest) {
        Flight flight = flightMapper.mapToModel(flightRequest);
        List<Seat> seats = new ArrayList<Seat>();
        for (int i = 0; i < flight.getTotalSeats(); i++) {
            seats.add(Seat.builder()
                    .seatNumber(i)
                    .isBooked(false)
                    .build());
        }
        flight.setSeats(seats);
        flight.setBookedSeats(0);
        flightRepository.save(flight);
        return flightMapper.mapToDTO(flight);
    }

    public FlightResponse getFlightById(String flightId) {
        Flight flight = flightRepository.findById(flightId).orElseThrow(()-> new FlightNotFoundException("Flight Doesn't Exist"));
        return flightMapper.mapToDTO(flight);
    }

    public List<FlightResponse> getAllFlights() {
        List<Flight> flights = flightRepository.findAll();
        if(flights.isEmpty()){
            throw new FlightNotFoundException("There are no flights yet!");
        }
        return  flights.stream().map(flightMapper::mapToDTO).toList();
    }

    public FlightResponse updateFlight(FlightRequest flightRequest) {
        Flight flight = flightMapper.mapToModel(flightRequest);
        Flight existingFlight = flightRepository.findById(flight.getFlightId())
                .orElseThrow(()-> new FlightNotFoundException("Flight Doesnt Exist"));

        existingFlight.setFlightNumber(flight.getFlightNumber());
        existingFlight.setTotalSeats(flight.getTotalSeats());
        existingFlight.setCompany(flightRequest.getCompany());

        flightRepository.save(existingFlight);

        return flightMapper.mapToDTO(existingFlight);
    }

    public void deleteFlightById(String flightId) {
        flightRepository.deleteById(flightId);
        scheduleServiceCommunicator.deleteScheduleByFlightId((flightId));
    }

    public void deleteAllFlights() {
        flightRepository.deleteAll();
        scheduleServiceCommunicator.deleteAllSchedules();
    }
}
