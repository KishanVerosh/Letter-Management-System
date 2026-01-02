package com.uoc.lms.repository;

import com.uoc.lms.model.Letter;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LetterRepository extends JpaRepository<Letter, Long> {
    Optional<Letter> findByReferenceNumber(String referenceNumber);
    boolean existsByReferenceNumber(String referenceNumber);
}
