package com.flight.booking.location.repository;

import com.flight.booking.location.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location,Integer> {
    Optional<Location> findByAirportName(String airportName);
}
