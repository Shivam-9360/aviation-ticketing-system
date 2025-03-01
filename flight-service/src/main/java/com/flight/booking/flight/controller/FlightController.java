package com.flight.booking.flight.controller;

import com.flight.booking.flight.dto.FlightRequest;
import com.flight.booking.flight.dto.FlightResponse;
import com.flight.booking.flight.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FlightController {
    private final FlightService flightService;

    @PostMapping("/flight")
    public ResponseEntity<FlightResponse> createFlight(@RequestBody FlightRequest flightRequest) {
        return ResponseEntity.ok(flightService.createFlight(flightRequest));
    }

    @GetMapping("/flights")
    public ResponseEntity<List<FlightResponse>> getAllFlights() {
        return ResponseEntity.ok(flightService.getAllFlights());
    }

    @GetMapping("/flight/{flightId}")
    public ResponseEntity<FlightResponse> getFlightById(@PathVariable String flightId) {
        return ResponseEntity.ok(flightService.getFlightById(flightId));
    }

    @PutMapping("/flight")
    public ResponseEntity<FlightResponse> updateFlight(@RequestBody FlightRequest flightRequest) {
        return ResponseEntity.ok(flightService.updateFlight(flightRequest));
    }

    @DeleteMapping("/flight/{flightId}")
    public ResponseEntity<String> deleteFlight(@PathVariable String flightId) {
        flightService.deleteFlightById(flightId);
        return ResponseEntity.ok("Flight Deleted");
    }

    @DeleteMapping("/flights")
    public ResponseEntity<String> deleteAllFlights() {
        flightService.deleteAllFlights();
        return ResponseEntity.ok("All flights Deleted");
    }
}
