package com.flight.booking.airport.service;

import com.flight.booking.airport.dto.AirportRequest;
import com.flight.booking.airport.dto.AirportResponse;
import com.flight.booking.airport.entity.Airport;
import com.flight.booking.airport.exception.AirportDoesntExistException;
import com.flight.booking.airport.exception.AirportAlreadyExistsException;
import com.flight.booking.airport.feignClient.ScheduleServiceCommunicator;
import com.flight.booking.airport.mapper.AirportMapper;
import com.flight.booking.airport.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AirportServiceImpl implements AirportService{

    private final AirportRepository repo;
private final AirportMapper airportMapper;

@Autowired
private ScheduleServiceCommunicator scheduleServiceCommunicator;

    @Override
    public List<AirportResponse> getAllAirports() {
        List<Airport> airports = repo.findAll();
        if(airports.isEmpty()){
            throw new AirportDoesntExistException("There are no Airports yet");
        }
        return airports.stream().map(airportMapper::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public AirportResponse createAirport(AirportRequest airport) {
        Airport createdAirport = repo.save(airportMapper.mapToModel(airport));
        return airportMapper.mapToDTO(createdAirport);
    }

    @Override
    public void deleteAirportById(int id) {
        if(repo.findById(id).isEmpty()){
            throw new AirportDoesntExistException("Airport with Name " + id + " already exists");
        }
        repo.deleteById(id);
        scheduleServiceCommunicator.deleteScheduleById(id);
    }

    @Override
    public AirportResponse getAirportById(int id){
        return airportMapper.mapToDTO(repo.findById(id)
                .orElseThrow(() -> new AirportDoesntExistException("Airport with ID " + id + " doesn't exist")));
    }

    @Override
    public AirportResponse updateAirport(AirportRequest airport) {
        Airport existingAirport = repo.findById(airport.getId())
                .orElseThrow(() -> new AirportDoesntExistException("Cannot update. Airport not found with ID: " + airport.getId()));

        existingAirport.setAirportName(airport.getAirportName());
        existingAirport.setCity(airport.getCity());
        existingAirport.setCountry(airport.getCountry());

        return airportMapper.mapToDTO(repo.save(existingAirport));
    }
}
