package com.api.collections.services.exceptions;

public class CannotInsertItemException extends Exception
{
    public CannotInsertItemException()
    {
        super("Cannot insert that item into the database.");
    }
}
