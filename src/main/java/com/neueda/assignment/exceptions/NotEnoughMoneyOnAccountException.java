package com.neueda.assignment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotEnoughMoneyOnAccountException extends Throwable {
    public NotEnoughMoneyOnAccountException(String errorMessage) {
        super(errorMessage);
    }

}
