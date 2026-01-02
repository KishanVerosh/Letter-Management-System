package com.uoc.lms.repository;

import com.uoc.lms.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
    Location findByLocation(String location);
}

