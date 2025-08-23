package com.api.collections.services.exceptions;

public class BadIdException extends Exception
{
    public BadIdException()
    {
        super("A null or negative ID was supplied.");
    }
}
