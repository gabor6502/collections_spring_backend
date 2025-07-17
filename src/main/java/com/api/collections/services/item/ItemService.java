package com.api.collections.services.item;

import com.api.collections.entities.Category;
import com.api.collections.entities.Creator;
import com.api.collections.entities.Item;
import com.api.collections.serializables.CategorySerializable;
import com.api.collections.serializables.CreatorSerializable;
import com.api.collections.serializables.ItemSerializable;
import com.api.collections.services.exceptions.CannotInsertException;
import com.api.collections.services.exceptions.ItemNotFoundException;

import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService 
{

    @Autowired
    private ItemRepository itemRepo;
    
    // CRUD!
    
    @Transactional
    public void create(ItemSerializable addMe) throws CannotInsertException
    {
        try {
            itemRepo.save(addMe.toEntity());
        } catch (IllegalArgumentException iae)
        {
            throw new CannotInsertException();
        }
    }
    
    public List<ItemSerializable> getAll()
    {
        return serializeResults(itemRepo.findAll());
    }
    
    public ItemSerializable getItem(Long id) throws ItemNotFoundException
    {
        return new ItemSerializable(findItemById(id));
    }
    
    @Transactional
    public void updateItemData(ItemSerializable updated) throws ItemNotFoundException
    {
        // "When we use findById() to retrieve an entity within a transactional 
        // method, the returned entity is managed by the persistence provider."
        //https://www.baeldung.com/spring-data-crud-repository-save#updateInstance
        //https://www.baeldung.com/hibernate-entity-lifecycle#managed-entity
        
        Item item = findItemById(updated.getId());
        
        // update everything but the image, as we have a separate procedure for that and don't need to rewrite the bytes every time
        item.setName(updated.getName());
        item.setNotes(updated.getNotes());
        item.setDate(updated.getDate());
        
        ArrayList<Category> category_ents = new ArrayList<>();
        for (CategorySerializable c : updated.getCategories())
        {
            category_ents.add(new Category(c.getId(), c.getName()));
        }
        item.setCategories(category_ents);
        
        ArrayList<Creator> creator_ents = new ArrayList<>();
        for (CreatorSerializable c : updated.getCreators())
        {
            creator_ents.add(new Creator(c.getId(), c.getName(), c.getTitle()));
        }
        item.setCreators(creator_ents);
    }
    
    @Transactional
    public void updateItemImage(ItemSerializable updated) throws ItemNotFoundException
    {
        findItemById(updated.getId()).setImageBytes(updated.getImage());
    }
    
    @Transactional
    public void deleteItem(Long id)
    {
        itemRepo.deleteById(id);
    }
    
    // -- helper methods --
    
    private Item findItemById(Long id) throws ItemNotFoundException
    {
        return itemRepo.findById(id)
            .orElseThrow(() -> new ItemNotFoundException(id));
    }
    
    private List<ItemSerializable> serializeResults(List<Item> items)
    {
        if (items == null)
        {
            return null; //right from the get-go, don't do anything if we have nothing to work with
        }
        
        ArrayList<ItemSerializable> items_ser = new ArrayList<>();
        
        for(Item i : items)
        {
            items_ser.add(new ItemSerializable(i));
        }
        
        return items_ser;
    }
}
