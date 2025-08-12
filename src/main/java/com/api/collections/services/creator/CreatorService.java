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
    
    // - CRD -
    
    @Transactional
    public void create(CreatorSerializable creator) throws CannotInsertCreatorException
    {
        
    }
    
    @Transactional
    public void update(CreatorSerializable updated) throws CreatorNotFoundException
    {
        
    }
    
    @Transactional
    public void delete(Long id) throws CreatorNotFoundException
    {
        // if no one else is using this creator, then we can fully expel it from the DB
    }
}
