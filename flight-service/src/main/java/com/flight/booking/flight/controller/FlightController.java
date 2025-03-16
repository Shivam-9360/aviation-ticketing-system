package com.flight.booking.flight.controller;

import com.flight.booking.flight.dto.DTO;
import com.flight.booking.flight.dto.FlightRequest;
import com.flight.booking.flight.dto.FlightResponse;
import com.flight.booking.flight.service.FlightService;
import com.flight.booking.flight.service.FlightServiceImpl;
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
    public ResponseEntity<DTO<FlightResponse>> createFlight(@RequestBody FlightRequest flightRequest) {
        FlightResponse createdFlight =  flightService.createFlight(flightRequest);
        return ResponseEntity.ok(DTO.<FlightResponse>builder()
                .success(true)
                .message("Flight Created Successfully")
                .data(createdFlight)
                .build());
    }

    @GetMapping("/flights")
    public ResponseEntity<DTO<List<FlightResponse>>> getAllFlights() {
        List<FlightResponse> flights = flightService.getAllFlights();
        return ResponseEntity.ok(DTO.<List<FlightResponse>>builder()
                .success(true)
                .message("Flight fetched Successfully")
                .data(flights)
                .build());
    }

    @GetMapping("/flight/{flightId}")
    public ResponseEntity<DTO<FlightResponse>> getFlightById(@PathVariable String flightId) {
        FlightResponse fetchedFlight =   flightService.getFlightById(flightId);
        return ResponseEntity.ok(DTO.<FlightResponse>builder()
                .success(true)
                .message("Flight Created Successfully")
                .data(fetchedFlight)
                .build());
    }

    @PutMapping("/flight")
    public ResponseEntity<DTO<FlightResponse>> updateFlight(@RequestBody FlightRequest flightRequest) {
        FlightResponse updatedFlight = flightService.updateFlight(flightRequest);
        return ResponseEntity.ok(DTO.<FlightResponse>builder()
                .success(true)
                .message("Flight Updated Successfully")
                .data(updatedFlight)
                .build());
    }

    @DeleteMapping("/flight/{flightId}")
    public ResponseEntity<DTO<String>> deleteFlight(@PathVariable String flightId) {
        flightService.deleteFlightById(flightId);
        return ResponseEntity.ok(DTO.<String>builder()
                .success(true)
                .message("Flight Deleted Successfully")
                .build());
    }

    @DeleteMapping("/flights")
    public ResponseEntity<DTO<String>> deleteAllFlights() {
        flightService.deleteAllFlights();
        return ResponseEntity.ok(DTO.<String>builder()
                .success(true)
                .message("Flight Deleted Successfully")
                .build());
    }
}
