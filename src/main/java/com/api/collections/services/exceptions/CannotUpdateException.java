package com.api.collections.services.exceptions;

public class CannotUpdateException extends Exception
{
    public CannotUpdateException()
    {
        super("Cannot update that item in the database.");
    }
}
