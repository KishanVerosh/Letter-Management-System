package com.uoc.lms.repository;

import com.uoc.lms.model.UserLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLocationRepository extends JpaRepository<UserLocation, Long> {
}

