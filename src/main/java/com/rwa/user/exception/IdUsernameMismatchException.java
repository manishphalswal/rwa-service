package com.rwa.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IdUsernameMismatchException extends RuntimeException {

    private static final String MESSAGE = "User Id and Username pair doesn't match";

    public IdUsernameMismatchException() {
        super(MESSAGE);
    }
}
