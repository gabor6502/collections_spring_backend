package com.api.collections.services.exceptions;

public class ItemNotFoundException extends Exception
{
    public ItemNotFoundException(Long id)
    {
        super("Cannot find item with id "+id);
    }
}
