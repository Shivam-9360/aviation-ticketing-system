package com.flight.booking.airport.controller;

import com.flight.booking.airport.dto.AirportRequest;
import com.flight.booking.airport.dto.AirportResponse;
import com.flight.booking.airport.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AirportController {

    private final AirportService service;

    @GetMapping("/airports")
    public ResponseEntity<List<AirportResponse>> getAllAirports() {
        List<AirportResponse> airports = service.getAllAirports();
        return ResponseEntity.ok(airports);
    }

    @GetMapping("/airport/{id}")
    public ResponseEntity<AirportResponse> getUserById(@PathVariable int id){
        AirportResponse airport =  service.getAirportById(id);
        return  ResponseEntity.ok(airport);
    }

    @PostMapping("/airport")
    public ResponseEntity<AirportResponse> createUser(@RequestBody AirportRequest airport) {
        AirportResponse createdAirport = service.createAirport(airport);
        return ResponseEntity.status(201).body(createdAirport);
    }

    @PutMapping("/airport")
    public ResponseEntity<AirportResponse> updateUser(@RequestBody AirportRequest airport){
        AirportResponse updateUser = service.updateAirport(airport);
        return ResponseEntity.status(201).body(updateUser);
    }

    @DeleteMapping("/airport/{id}")
    private ResponseEntity<String> deleteUserById(@PathVariable int id){
        service.deleteAirportById(id);
        return ResponseEntity.ok("Airport Deleted");
    }

}
