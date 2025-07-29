package com.api.collections.services.exceptions.notFound;


public abstract class EntityNotFoundException extends Exception
{
    public EntityNotFoundException(String entName, Long id)
    {
        super("Cannot find "+entName+" with id "+id+".");
    }
}
