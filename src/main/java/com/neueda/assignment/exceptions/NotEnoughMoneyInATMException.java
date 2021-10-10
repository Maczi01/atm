package com.neueda.assignment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotEnoughMoneyInATMException extends Exception {
    public NotEnoughMoneyInATMException(String errorMessage) {
        super(errorMessage);
    }
}
