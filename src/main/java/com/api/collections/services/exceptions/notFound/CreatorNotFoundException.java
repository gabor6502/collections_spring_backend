package com.api.collections.services.exceptions.notFound;

public class CreatorNotFoundException extends EntityNotFoundException 
{
    public CreatorNotFoundException(Long id)
    {
        super("Creator", id);
    }
}
