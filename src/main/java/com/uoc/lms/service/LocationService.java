package com.uoc.lms.service;


import com.uoc.lms.model.Location;
import com.uoc.lms.repository.LocationRepository;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class LocationService {
    private final LocationRepository locationRepo;

    public LocationService(LocationRepository locationRepo) {
        this.locationRepo = locationRepo;
    }
    
    public List<Location> getAllLocations() {
        return locationRepo.findAll();
    }

    
    public Location getLocationById(Long id) {
        return locationRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not found"));
    }

    
    public Location saveLocation(Location location) {
        return locationRepo.save(location);
    }
}
