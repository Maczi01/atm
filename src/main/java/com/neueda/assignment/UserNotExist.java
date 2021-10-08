package com.neueda.assignment;

public class UserNotExist extends Throwable {
    public UserNotExist(String errorMessage) {
        super(errorMessage);
    }
}
