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
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR,
            reason="Server failed to process request")
    @ExceptionHandler(Exception.class)
    public void handleException(Exception ex) {
        ex.printStackTrace();
    }

    @ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR,
            reason="Server failed to process request")
    @ExceptionHandler(DataIntegrityViolationException.class)
    public void handleException(DataIntegrityViolationException ex) {
        ex.printStackTrace();
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
        ex.printStackTrace();
    }

    @ResponseStatus(value= HttpStatus.NOT_FOUND,
            reason="Resource not found")
    @ExceptionHandler(ResourceNotFoundException.class)
    public void handleException(ResourceNotFoundException ex) {
    }

    @ResponseStatus(value= HttpStatus.UNAUTHORIZED,
            reason="User is unauthorized to access this resource")
    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    public void handleException(HttpClientErrorException.Unauthorized ex) {
    }
}
