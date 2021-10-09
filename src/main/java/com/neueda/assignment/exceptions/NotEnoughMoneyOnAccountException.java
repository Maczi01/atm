package com.neueda.assignment.exceptions;

public class NotEnoughMoneyOnAccountException extends Exception {
    public NotEnoughMoneyOnAccountException(String errorMessage) {
        super(errorMessage);
    }
}
