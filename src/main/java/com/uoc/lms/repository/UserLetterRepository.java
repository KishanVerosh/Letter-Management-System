package com.uoc.lms.repository;

import com.uoc.lms.model.User;
import com.uoc.lms.model.UserLetter;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLetterRepository extends JpaRepository<UserLetter, Long> {
    List<UserLetter> findByUser(User user);

}
