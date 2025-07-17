package com.api.collections.services;

// Handling the business logic in some ways beyond the boilerplate that 
// @Repository and JpaRepository provide. Hence the full implementation 
// of services

import com.api.collections.entities.GuitarEntity;
import com.api.collections.serializables.GuitarSerializable;
import com.api.collections.serializables.ItemSerializable;
import com.api.collections.services.exceptions.CannotInsertException;
import com.api.collections.services.exceptions.CannotUpdateException;
import com.api.collections.services.exceptions.ItemNotFoundException;
import jakarta.persistence.EntityExistsException;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GuitarService extends ItemService
{
    @Override
    @Transactional
    public List<GuitarSerializable> getAll()
    {
        List<GuitarEntity> guitars = em.createQuery("SELECT *", GuitarEntity.class)
                .getResultList();
        
        List<GuitarSerializable> guitars_ser = new ArrayList<>();
        
        for (GuitarEntity guitar : guitars)
        {
            guitars_ser.add(new GuitarSerializable(guitar));
        }
        
        return guitars_ser;
    }
    
    @Override
    @Transactional
    public GuitarSerializable get(Long id)
    {
        GuitarEntity guitar = em.find(GuitarEntity.class, id);
        
        return guitar == null ? null : new GuitarSerializable(guitar);
    }

    @Override
    @Transactional
    public void create(ItemSerializable guitar) throws CannotInsertException
    {
        try
        {
            em.persist((GuitarEntity)guitar.toEntity());
        } catch (EntityExistsException eee)
        {
            throw new CannotInsertException();
        }
    }

    @Override
    @Transactional
    public void updateName(Long id, String name) throws CannotUpdateException 
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    @Transactional
    public void updateNotes(Long id, String notes) throws CannotUpdateException 
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    @Transactional
    public void updateImage(Long id, byte[] image) throws CannotUpdateException 
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    @Transactional
    public boolean delete(Long id) throws ItemNotFoundException 
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}
