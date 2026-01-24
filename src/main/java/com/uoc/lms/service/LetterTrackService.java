package com.uoc.lms.service;

import com.uoc.lms.model.LetterTrack;
import com.uoc.lms.repository.LetterTrackRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LetterTrackService {

    private final LetterTrackRepository trackRepository;

    public LetterTrackService(LetterTrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    public List<LetterTrack> getTrackByLetterId(Long letterId) {
        return trackRepository.findByLetterIdOrderByTimeAsc(letterId);
    }
}
