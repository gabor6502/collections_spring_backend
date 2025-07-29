package com.api.collections.services.exceptions.notFound;

public class ItemNotFoundException extends EntityNotFoundException
{
    public ItemNotFoundException(Long id)
    {
        super("Item", id);
    }
}
