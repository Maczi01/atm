package com.neueda.assignment.exceptions;

public class CardNotExistException extends Throwable {
    public CardNotExistException(String errorMessage) {
        super(errorMessage);
    }
}
