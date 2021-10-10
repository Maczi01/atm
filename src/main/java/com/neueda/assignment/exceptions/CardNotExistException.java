package com.neueda.assignment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CardNotExistException extends Throwable {
    public CardNotExistException(String errorMessage) {
        super(errorMessage);
    }
}
