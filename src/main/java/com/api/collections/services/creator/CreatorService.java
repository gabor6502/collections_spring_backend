package com.api.collections.services.creator;

import com.api.collections.entities.Creator;
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
    
    // - CR - 
    
    @Transactional
    public void create(CreatorSerializable addMe) throws CannotInsertCreatorException
    {
        try
        {
            em.persist(addMe.toEntity());
        } catch (IllegalArgumentException iae)
        {
            throw new CannotInsertCreatorException();
        }
    }
    
    public CreatorSerializable getCategory(Long id) throws CreatorNotFoundException
    {
        return new CreatorSerializable(findCreatorById(id));
    }
    
    public CreatorSerializable getCreatorByName(String name)
    {
        return 
            em.createQuery("SELECT new CreatorSerializable(c) FROM Creator c WHERE c.name = :creatName", CreatorSerializable.class)
                .setParameter("creatName", name)
                .getSingleResult(); // since name is unique in DB, should be only one result
    }
    
    // -- helper methods --
    
    private Creator findCreatorById(Long id) throws CreatorNotFoundException
    {

       Creator findMe = em.find(Creator.class, id);
       
       if (findMe == null)
       {
           throw new CreatorNotFoundException(id);
       }
       
       return findMe;
    }
}
