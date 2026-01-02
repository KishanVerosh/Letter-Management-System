package com.uoc.lms.service;

import com.uoc.lms.model.Location;
import com.uoc.lms.model.Role;
import com.uoc.lms.model.User;
import com.uoc.lms.model.UserLocation;
import com.uoc.lms.repository.LocationRepository;
import com.uoc.lms.repository.UserLocationRepository;
import com.uoc.lms.repository.UserRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository repo;
    private final LocationRepository locationRepo;
    private final UserLocationRepository userLocationRepo;

    public AuthService(
            UserRepository repo,
            LocationRepository locationRepo,
            UserLocationRepository userLocationRepo) {
        this.repo = repo;
        this.locationRepo = locationRepo;
        this.userLocationRepo = userLocationRepo;
    }

    // Login validation
    public boolean validate(String username, String password) {
        User user = repo.findByUsername(username);
        if (user == null) return false;

        return user.getPassword().equals(password);
    }

    // Registration
    public boolean register(String username, String password) {

        // check if username already exists
        if (repo.findByUsername(username) != null) {
            return false;
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password); // (we'll hash later)
        user.setRole(Role.Normal);

        repo.save(user);

        Location defaultLocation = locationRepo.findById(1L)
        .orElseThrow(() -> new RuntimeException("Default location not found"));

        UserLocation userLocation = new UserLocation();
        userLocation.setUser(user);
        userLocation.setLocation(defaultLocation);

        userLocationRepo.save(userLocation);
        
        return true;
    }

    public String userRole(String username){
        User user = repo.findByUsername(username);
        return user.getRole().toString();
    }

    public List<User> getAllUsers() {
        return repo.findAll();
    }
}
