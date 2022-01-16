package com.rwa.common.advice;

import com.rwa.exception.InvalidRequestException;
import com.rwa.referencedata.exception.ResourceNotFoundException;
import com.rwa.user.exception.IdUsernameMismatchException;
import com.rwa.user.exception.UserNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(value= HttpStatus.CONFLICT,
            reason="User already exists with given username")
    @ExceptionHandler(DataIntegrityViolationException.class)
    public void handleException(DataIntegrityViolationException ex) {

    }

    @ResponseStatus(value= HttpStatus.NOT_FOUND,
            reason="User not exists for given id")
    @ExceptionHandler(UserNotFoundException.class)
    public void handleException(UserNotFoundException ex) {

    }

    @ResponseStatus(value= HttpStatus.BAD_REQUEST,
            reason="User Id and Username pair doesn't match")
    @ExceptionHandler(IdUsernameMismatchException.class)
    public void handleException(IdUsernameMismatchException ex) {

    }

    @ResponseStatus(value= HttpStatus.BAD_REQUEST,
            reason="Invalid request payload")
    @ExceptionHandler(InvalidRequestException.class)
    public void handleException(InvalidRequestException ex) {

    }

    @ResponseStatus(value= HttpStatus.NOT_FOUND,
            reason="Resource not found")
    @ExceptionHandler(ResourceNotFoundException.class)
    public void handleException(ResourceNotFoundException ex) {

    }
}
