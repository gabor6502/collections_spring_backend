package com.api.collections.controllers.handlers;

import com.api.collections.services.exceptions.cannotInsert.CannotInsertItemException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CannotInsertItemExceptionHandler 
{
    @ExceptionHandler(CannotInsertItemException.class)
    public ResponseEntity<String> handleException(CannotInsertItemException ciie)
    {
        return new ResponseEntity(ciie, HttpStatus.BAD_REQUEST);
    }
}
