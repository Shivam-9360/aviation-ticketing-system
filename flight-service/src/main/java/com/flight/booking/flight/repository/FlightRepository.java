package com.flight.booking.flight.repository;

import com.flight.booking.flight.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Integer> {
}
