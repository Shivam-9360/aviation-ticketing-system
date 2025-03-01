package com.flight.booking.location.service;

import com.flight.booking.location.entity.Location;
import com.flight.booking.location.exception.LocationAlreadyExistsException;
import com.flight.booking.location.exception.NoLocationsFoundException;
import com.flight.booking.location.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService{

    private final LocationRepository locationRepository;

    @Override
    public List<Location> getAllLocations() {
        List<Location> locations =  locationRepository.findAll();
        if(locations.isEmpty()){
            throw new NoLocationsFoundException("No Locations Found");
        }
        return locations;
    }

    @Override
    public Location createLocation(Location location) {
        if(locationRepository.findByAirportName(location.getAirportName()).isPresent()){
            throw new LocationAlreadyExistsException("Airport with Name " + location.getAirportName() + " already exists");
        }
        return locationRepository.save(location);
    }

    @Override
    public void deleteLocationById(int id) {

    }

    @Override
    public Location getLocationById(int id) {
        return null;
    }
}
