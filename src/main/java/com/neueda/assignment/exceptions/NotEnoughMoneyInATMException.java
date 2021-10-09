package com.neueda.assignment.exceptions;

public class NotEnoughMoneyInATMException extends Exception {
    public NotEnoughMoneyInATMException(String errorMessage) {
        super(errorMessage);
    }
}
