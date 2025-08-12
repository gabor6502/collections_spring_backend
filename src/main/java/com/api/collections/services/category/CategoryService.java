package com.api.collections.services.category;

import com.api.collections.entities.Category;
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
    
    // - CRD - 
    
    @Transactional
    public void create(CategorySerializable creator) throws CannotInsertCategoryException
    {
        try
        {
            em.persist(creator.toEntity());
        } catch (IllegalArgumentException iae)
        {
            throw new CannotInsertCategoryException();
        }
    }
    
    public CategorySerializable getCategory(Long id) throws CategoryNotFoundException
    {
        return new CategorySerializable(findCategoryById(id));
    }
    
    @Transactional
    public void delete(Long id) throws CategoryNotFoundException
    {
        // if no one else is using this category, then we can fully expel it from the DB
        
    }
    
    // -- helper methods --
    
    private Category findCategoryById(Long id) throws CategoryNotFoundException
    {

       Category findMe = em.find(Category.class, id);
       
       if (findMe == null)
       {
           throw new CategoryNotFoundException(id);
       }
       
       return findMe;
    }
}
