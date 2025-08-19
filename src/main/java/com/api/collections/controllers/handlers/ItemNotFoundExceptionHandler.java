package com.api.collections.controllers.handlers;

import com.api.collections.services.exceptions.notFound.ItemNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ItemNotFoundExceptionHandler 
{
    @ExceptionHandler(value = ItemNotFoundException.class)
    public ResponseEntity<String> handleException(ItemNotFoundException itnfe)
    {
        return new ResponseEntity(itnfe, HttpStatus.BAD_REQUEST);
    }
}
