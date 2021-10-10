package com.neueda.assignment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class WrongPinException extends Exception {
    public WrongPinException(String errorMessage) {
        super(errorMessage);
    }


}
