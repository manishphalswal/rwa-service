package com.rwa.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    private static final String MESSAGE = "User not found for Id: ";

    public UserNotFoundException(final String id) {
        super(MESSAGE+id);
    }
}
