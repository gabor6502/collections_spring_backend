package com.api.collections.services;

// Handling the business logic in some ways beyond the boilerplate that 
// @Repository and JpaRepository provide. Hence the full implementation 
// of services

import com.api.collections.entities.GuitarEntity;
import com.api.collections.serializables.GuitarSerializable;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GuitarService 
{
    @PersistenceContext
    protected EntityManager em;
    
    @Transactional
    public void create(GuitarSerializable guitarSer)
    {
        if (guitarSer != null)
        {
            em.persist(new GuitarEntity(guitarSer.getName(), 
                                        guitarSer.getModel(),
                                        guitarSer.getImageBytes(),
                                        guitarSer.getType()));
        }
    }
    
}
