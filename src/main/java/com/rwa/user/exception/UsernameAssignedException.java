package com.rwa.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UsernameAssignedException extends RuntimeException {

    private static final String MESSAGE = "Username already assigned";

    public UsernameAssignedException() {
        super(MESSAGE);
    }
}
