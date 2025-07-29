package com.api.collections.services.exceptions.cannotInsert;

public class CannotInsertItemException extends CannotInsertEntityException
{
    public CannotInsertItemException()
    {
        super("Item");
    }
}
