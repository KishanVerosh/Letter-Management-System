package com.uoc.lms.service;

import com.uoc.lms.model.Letter;
import com.uoc.lms.model.User;
import com.uoc.lms.model.UserLetter;
import com.uoc.lms.repository.LetterRepository;
import com.uoc.lms.repository.UserRepository;
import com.uoc.lms.repository.UserLetterRepository;
import com.uoc.lms.util.ReferenceGenerator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
@Service
public class LetterService {

    private final LetterRepository letterRepository;
    private final UserRepository userRepository;
    private final UserLetterRepository userLetterRepository;

    public LetterService(LetterRepository letterRepository,
                         UserRepository userRepository,
                         UserLetterRepository userLetterRepository) {
        this.letterRepository = letterRepository;
        this.userRepository = userRepository;
        this.userLetterRepository = userLetterRepository;
    }

    public void saveLetter(String title,
                           String sender,
                           String receiver,
                           String status,
                           String username) {

        String ref;
        do {
            ref = ReferenceGenerator.generate();
        } while (letterRepository.existsByReferenceNumber(ref));

        // save letter
        Letter letter = new Letter();
        letter.setTitle(title);
        letter.setSenderAddress(sender);
        letter.setReceiverAddress(receiver);
        letter.setLetterStatus(status);
        letter.setReferenceNumber(ref);

        letterRepository.save(letter);

        // find user
        User user = userRepository.findByUsername(username);

        // map user ↔ letter
        UserLetter userLetter = new UserLetter();
        userLetter.setUser(user);
        userLetter.setLetter(letter);

        userLetterRepository.save(userLetter);
    }

    public List<Letter> getLettersForUser(String username) {
    User user = userRepository.findByUsername(username);
    if (user == null) return new ArrayList<>();

    // Get all UserLetter mappings for this user
    List<UserLetter> userLetters = userLetterRepository.findByUser(user);

    // Extract letters
    List<Letter> letters = new ArrayList<>();
    for (UserLetter ul : userLetters) {
        letters.add(ul.getLetter());
    }

    return letters;
}

public boolean updateLetter(String referenceNumber, String letterType) {
        return letterRepository.findByReferenceNumber(referenceNumber)
                .map(letter -> {
                    //letter.setLetterType(letterType);
                    letter.setLetterStatus(letterType);
                    letterRepository.save(letter);
                    return true;
                }).orElse(false);
    }
    
}