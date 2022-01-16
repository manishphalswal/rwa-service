package com.rwa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidRequestException extends RuntimeException {

    private static final String MESSAGE = "Invalid Request Payload";

    public InvalidRequestException() {
        super(MESSAGE);
    }
}
