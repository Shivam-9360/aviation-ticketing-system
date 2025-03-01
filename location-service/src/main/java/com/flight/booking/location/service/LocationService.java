package com.flight.booking.location.service;

import com.flight.booking.location.entity.Location;

import java.util.List;

public interface LocationService {
    List<Location> getAllLocations();
    Location createLocation(Location location);
    void deleteLocationById(int id);
    Location getLocationById(int id);
}
