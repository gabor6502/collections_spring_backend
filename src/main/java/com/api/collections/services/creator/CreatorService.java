package com.api.collections.services.creator;

import com.api.collections.serializables.CreatorSerializable;
import com.api.collections.services.exceptions.cannotInsert.CannotInsertCreatorException;
import com.api.collections.services.exceptions.notFound.CreatorNotFoundException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;


@Service
public class CreatorService 
{
    @PersistenceContext
    protected EntityManager em; // default Flush Type is AUTO, so DB changes flushed before transaction commit and query execution
    
    // - CRUD -
    
    @Transactional
    public void create(CreatorSerializable creator) throws CannotInsertCreatorException
    {
        
    }
    
    public CreatorSerializable getCreator(Long id) throws CreatorNotFoundException
    {
        return null;
    }
    
    @Transactional
    public void update(CreatorSerializable updated) throws CreatorNotFoundException
    {
        
    }
    
    @Transactional
    public void delete(Long id) throws CreatorNotFoundException
    {
        
    }
}
