package com.api.collections.controllers;

import com.api.collections.controllers.exceptions.NoIdException;
import com.api.collections.serializables.ItemSerializable;
import com.api.collections.services.exceptions.cannotInsert.CannotInsertItemException;
import com.api.collections.services.exceptions.notFound.ItemNotFoundException;
import com.api.collections.services.item.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("item")
public class ItemController 
{
    @Autowired
    protected ItemService service;
    
    @PostMapping("")
    public ItemSerializable create(@RequestBody ItemSerializable created) throws CannotInsertItemException
    {
        return service.create(created);
    }
    
    @GetMapping("/{id}")
    public ItemSerializable getItem(@PathVariable Long id) throws ItemNotFoundException, NoIdException
    {
        if (id == null)
        {
            throw new NoIdException();
        }
            
        return service.getItem(id);
    }
    
    @PutMapping("/data")
    public ItemSerializable updateItem(@RequestBody ItemSerializable updated) throws ItemNotFoundException
    {
        return service.updateItemData(updated);
    }
    
    @PutMapping("/image")
    public ItemSerializable updateItemImage(@RequestBody ItemSerializable updated) throws ItemNotFoundException
    {
        return service.updateItemImage(updated);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) throws ItemNotFoundException
    {
        service.deleteItem(id);
    }
 
}
