package com.api.collections.controllers;

import com.api.collections.controllers.exceptions.NoIdException;
import com.api.collections.serializables.ItemSerializable;
import com.api.collections.services.exceptions.notFound.ItemNotFoundException;
import com.api.collections.services.item.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("item")
public class ItemController 
{
    @Autowired
    protected ItemService service;
    
    @GetMapping("/{id}")
    public ItemSerializable getItem(@PathVariable Long id) throws ItemNotFoundException, NoIdException
    {
        if (id == null)
        {
            throw new NoIdException();
        }
            
        return service.getItem(id);
    }
    
}
