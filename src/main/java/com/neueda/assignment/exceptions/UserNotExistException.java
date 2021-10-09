package com.neueda.assignment.exceptions;

public class UserNotExistException extends Exception {
    public UserNotExistException(String errorMessage) {
        super(errorMessage);
    }
}
