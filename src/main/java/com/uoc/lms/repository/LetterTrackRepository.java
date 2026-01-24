package com.uoc.lms.repository;

import com.uoc.lms.model.LetterTrack;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LetterTrackRepository extends JpaRepository<LetterTrack, Long> {

    List<LetterTrack> findByLetterIdOrderByTimeAsc(Long letterId);
}
