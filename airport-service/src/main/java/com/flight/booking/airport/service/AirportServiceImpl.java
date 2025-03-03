package com.flight.booking.airport.service;

import com.flight.booking.airport.entity.Airport;
import com.flight.booking.airport.exception.AirportDoesntExistException;
import com.flight.booking.airport.exception.AirportAlreadyExistsException;
import com.flight.booking.airport.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirportServiceImpl implements AirportService{

    private final AirportRepository repo;

    @Override
    public List<Airport> getAllAirports() {
        List<Airport> airports = repo.findAll();
        if(airports.isEmpty()){
            throw new AirportDoesntExistException("There are no Airports yet");
        }
        return  airports;
    }

    @Override
    public Airport createAirport(Airport airport) {
        return repo.save(airport);
    }

    @Override
    public void deleteAirportById(int id) {
        if(repo.findById(id).isEmpty()){
            throw new AirportDoesntExistException("Airport with Name " + id + " already exists");
        }
        repo.deleteById(id);
    }

    @Override
    public Airport getAirportById(int id){
        return repo.findById(id)
                .orElseThrow(() -> new AirportDoesntExistException("Airport with ID " + id + " doesn't exist"));
    }

    @Override
    public Airport updateAirport(Airport airport) {
        Airport existingAirport = repo.findById(airport.getId())
                .orElseThrow(() -> new AirportDoesntExistException("Cannot update. Airport not found with ID: " + airport.getId()));

        existingAirport.setAirportName(airport.getAirportName());
        existingAirport.setCity(airport.getCity());
        existingAirport.setCountry(airport.getCountry());

        return repo.save(existingAirport);
    }
}
