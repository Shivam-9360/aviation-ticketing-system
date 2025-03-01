package com.flight.booking.flight.service;


import com.flight.booking.flight.dto.SeatRequest;
import com.flight.booking.flight.dto.SeatResponse;
import com.flight.booking.flight.mapper.SeatMapper;
import com.flight.booking.flight.model.Seat;
import com.flight.booking.flight.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SeatService {
    private final SeatRepository seatRepository;
    private final SeatMapper seatMapper;

    public SeatResponse createSeat(SeatRequest seatRequest) {
        Seat seat = seatMapper.mapToModel(seatRequest);
        seatRepository.save(seat);
        return seatMapper.mapToDTO(seat);
    }

    public SeatResponse getSeatById(String seatId) {
        Seat seat = seatRepository.findById(seatId).orElse(null);
        return seat != null ? seatMapper.mapToDTO(seat) : null;
    }

    public List<SeatResponse> getAllSeats() {
        return seatRepository.findAll().stream().map(seatMapper::mapToDTO).collect(Collectors.toList());
    }

    public SeatResponse updateSeat(SeatRequest seatRequest) {
        Seat seat = seatMapper.mapToModel(seatRequest);
        Seat existingSeat = seatRepository.findById(seat.getSeatId()).orElse(null);

        if(existingSeat == null)
            return null;

        existingSeat.setSeatNumber(seat.getSeatNumber());
        existingSeat.setBooked(seat.isBooked());
        existingSeat.setType(seatRequest.getType());

        seatRepository.save(existingSeat);

        return seatMapper.mapToDTO(existingSeat);
    }

    public void deleteSeatById(String seatId) {
        seatRepository.deleteById(seatId);
    }

    public void deleteAllSeats() {
        seatRepository.deleteAll();
    }
}
