package com.api.collections.controllers.handlers;

import com.api.collections.controllers.exceptions.NoIdException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NoIdExceptionHandler 
{
    @ExceptionHandler(value = NoIdException.class)
    public ResponseEntity<String> handleException(NoIdException ide)
    {
        return new ResponseEntity(ide, HttpStatus.BAD_REQUEST);
    }
}
