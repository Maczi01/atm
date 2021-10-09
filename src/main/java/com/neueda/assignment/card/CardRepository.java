package com.neueda.assignment.card;

import com.neueda.assignment.exceptions.UserNotExistException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {



}