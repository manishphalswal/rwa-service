package com.rwa.exception.user;

public class UserAlreadyExistsException extends RuntimeException {
    private static final String MESSAGE = "User already exists with Username: ";

    public UserAlreadyExistsException(final String username) {
        super(MESSAGE+username);
    }
}
