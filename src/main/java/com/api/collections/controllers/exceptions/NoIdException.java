package com.api.collections.controllers.exceptions;

public class NoIdException extends Exception 
{
    public NoIdException()
    {
        super("ID not provided");
    }
}
