package com.api.collections.services.category;

import com.api.collections.serializables.CategorySerializable;
import com.api.collections.services.exceptions.cannotInsert.CannotInsertCategoryException;
import com.api.collections.services.exceptions.notFound.CategoryNotFoundException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
public class CategoryService 
{
    
    @PersistenceContext
    protected EntityManager em; // default Flush Type is AUTO, so DB changes flushed before transaction commit and query execution
    
    // - CRUD -
    
    @Transactional
    public void create(CategorySerializable creator) throws CannotInsertCategoryException
    {
        
    }
    
    public CategorySerializable getCreator(Long id) throws CategoryNotFoundException
    {
        return null;
    }
    
    @Transactional
    public void update(CategorySerializable updated) throws CategoryNotFoundException
    {
        
    }
    
    @Transactional
    public void delete(Long id) throws CategoryNotFoundException
    {
        
    }
}
