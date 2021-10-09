package com.neueda.assignment.exceptions;

public class WrongAmountException extends Exception {
    public WrongAmountException(String errorMessage) {
        super(errorMessage);
    }
}
