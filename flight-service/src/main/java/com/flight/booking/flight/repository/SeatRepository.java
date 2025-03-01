package com.flight.booking.flight.repository;

import com.flight.booking.flight.model.Seat;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SeatRepository extends MongoRepository<Seat, String> {
}
