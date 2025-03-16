package com.flight.booking.flight.repository;

import com.flight.booking.flight.model.Flight;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FlightRepository extends MongoRepository<Flight, String> {
}
