package com.flight.booking.flight.controller;

import com.flight.booking.flight.dto.SeatRequest;
import com.flight.booking.flight.dto.SeatResponse;
import com.flight.booking.flight.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SeatController {
    private final SeatService seatService;

    @PostMapping("/seat")
    public ResponseEntity<SeatResponse> createSeat(@RequestBody SeatRequest seatRequest) {
        return ResponseEntity.ok(seatService.createSeat(seatRequest));
    }

    @GetMapping("/seats")
    public ResponseEntity<List<SeatResponse>> getAllSeats() {
        return ResponseEntity.ok(seatService.getAllSeats());
    }

    @GetMapping("/seat/{seatId}")
    public ResponseEntity<SeatResponse> getSeatById(@PathVariable String seatId) {
        return ResponseEntity.ok(seatService.getSeatById(seatId));
    }

    @PutMapping("/seat")
    public ResponseEntity<SeatResponse> updateSeat(@RequestBody SeatRequest seatRequest) {
        return ResponseEntity.ok(seatService.updateSeat(seatRequest));
    }

    @DeleteMapping("/seat/{seatId}")
    public ResponseEntity<String> deleteSeat(@PathVariable String seatId) {
        seatService.deleteSeatById(seatId);
        return ResponseEntity.ok("Seat Deleted");
    }

    @DeleteMapping("/seats")
    public ResponseEntity<String> deleteAllSeats() {
        seatService.deleteAllSeats();
        return ResponseEntity.ok("All seats Deleted");
    }
}
