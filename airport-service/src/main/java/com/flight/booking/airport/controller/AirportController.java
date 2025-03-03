package com.flight.booking.airport.controller;

import com.flight.booking.airport.entity.Airport;
import com.flight.booking.airport.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AirportController {
    @Autowired
    private AirportService service;

    @GetMapping("/airports")
    public ResponseEntity<List<Airport>> getAllAirports() {
        List<Airport> airports = service.getAllAirports();
        return ResponseEntity.ok(airports);
    }

    @GetMapping("/airport/{id}")
    public ResponseEntity<Airport> getUserById(@PathVariable int id){
        Airport airport =  service.getAirportById(id);
        return  ResponseEntity.ok(airport);
    }

    @PostMapping("/airport")
    public ResponseEntity<Airport> createUser(@RequestBody Airport airport) {
        Airport createdAirport = service.createAirport(airport);
        return ResponseEntity.status(201).body(createdAirport);
    }

    @PutMapping("/airport")
    public ResponseEntity<Airport> updateUser(@RequestBody Airport airport){
        Airport updateUser = service.updateAirport(airport);
        return ResponseEntity.status(201).body(updateUser);
    }

    @DeleteMapping("/airport/{id}")
    private ResponseEntity<String> deleteUserById(@PathVariable int id){
        service.deleteAirportById(id);
        return ResponseEntity.ok("Airport Deleted");
    }

}
