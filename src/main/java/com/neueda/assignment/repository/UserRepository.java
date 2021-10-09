package com.neueda.assignment.repository;

import com.neueda.assignment.exceptions.UserNotExistException;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

    double checkBalance() throws UserNotExistException;

}
