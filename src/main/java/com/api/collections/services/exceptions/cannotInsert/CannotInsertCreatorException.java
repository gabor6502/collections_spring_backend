package com.api.collections.services.exceptions.cannotInsert;

public class CannotInsertCreatorException extends CannotInsertEntityException
{
    public CannotInsertCreatorException() 
    {
        super("Creator");
    }
}
