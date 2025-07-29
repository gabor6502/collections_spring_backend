package com.api.collections.services.exceptions.cannotInsert;

public abstract class CannotInsertEntityException extends Exception
{
    public CannotInsertEntityException(String entName)
    {
        super("Cannot insert "+entName+" into DB.");
    }
}
