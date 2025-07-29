package com.api.collections.services.exceptions.notFound;

public class CategoryNotFoundException extends EntityNotFoundException 
{
    public CategoryNotFoundException(Long id)
    {
        super("Category", id);
    }
}
