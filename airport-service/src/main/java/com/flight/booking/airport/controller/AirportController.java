package com.flight.booking.airport.controller;

import com.flight.booking.airport.dto.AirportRequest;
import com.flight.booking.airport.dto.AirportResponse;
import com.flight.booking.airport.dto.DTO;
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
    public ResponseEntity<DTO<List<AirportResponse>>> getAllAirports() {
        List<AirportResponse> airports = service.getAllAirports();
        return ResponseEntity.ok(DTO.<List<AirportResponse>>builder()
                .success(true)
                .message("Airports Fetched Successfully")
                .data(airports)
                .build());
    }

    @GetMapping("/airport/{id}")
    public ResponseEntity<DTO<AirportResponse>> getAirportById(@PathVariable int id){
        AirportResponse airport =  service.getAirportById(id);
        return  ResponseEntity.ok(DTO.<AirportResponse>builder()
                .success(true)
                .message("Airport Fetched Successfully")
                .data(airport)
                .build());
    }

    @PostMapping("/airport")
    public ResponseEntity<DTO<AirportResponse>> createAirport(@RequestBody AirportRequest airport) {
        AirportResponse createdAirport = service.createAirport(airport);
        return ResponseEntity.status(201).body(DTO.<AirportResponse>builder()
                .success(true)
                .message("Airport Created Successfully")
                .data(createdAirport)
                .build());
    }

    @PutMapping("/airport")
    public ResponseEntity<DTO<AirportResponse>> updateAirport(@RequestBody AirportRequest airport){
        AirportResponse updatedAirport = service.updateAirport(airport);
        return ResponseEntity.status(201).body(DTO.<AirportResponse>builder()
                .success(true)
                .message("Airport Updated Successfully")
                .data(updatedAirport)
                .build());
    }

    @DeleteMapping("/airport/{id}")
    private ResponseEntity<DTO<String>> deleteAirportById(@PathVariable int id){
        service.deleteAirportById(id);
        return ResponseEntity.ok(DTO.<String>builder()
                        .success(true)
                        .message("Airport with id " + id + " deleted successfully")
                        .data(null)
                .build());
    }

}
