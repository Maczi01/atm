package com.neueda.assignment.exceptions;

public class NotEnoughMoneyOnAccountException extends Throwable {
    public NotEnoughMoneyOnAccountException(String errorMessage) {
        super(errorMessage);
    }

}
