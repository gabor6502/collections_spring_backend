package com.api.collections.services.exceptions;

public class CannotInsertException extends Exception
{
    public CannotInsertException()
    {
        super("Cannot insert that item into the database.");
    }
}
