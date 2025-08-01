package com.api.collections.services.item;

import com.api.collections.entities.Category;
import com.api.collections.entities.Creator;
import com.api.collections.entities.Item;
import com.api.collections.serializables.CategorySerializable;
import com.api.collections.serializables.CreatorSerializable;
import com.api.collections.serializables.ItemSerializable;
import com.api.collections.services.exceptions.cannotInsert.CannotInsertItemException;
import com.api.collections.services.exceptions.notFound.ItemNotFoundException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ItemService 
{

    @PersistenceContext
    protected EntityManager em; // default Flush Type is AUTO, so DB changes flushed before transaction commit and query execution
    
    // - CRUD -
    
    @Transactional
    public void create(ItemSerializable addMe) throws CannotInsertItemException
    {
        try {
            em.persist(addMe.toEntity());
        } catch (IllegalArgumentException iae)
        {
            throw new CannotInsertItemException();
        }
    }
    
    public ItemSerializable getItem(Long id) throws ItemNotFoundException
    {
        return new ItemSerializable(findItemById(id));
    }
    
    @Transactional
    public void updateItemData(ItemSerializable updated) throws ItemNotFoundException
    {

        // the item found by its ID will be tracked and persisted in the DB by the entity manager as we change it here
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
    public void deleteItem(Long id) throws ItemNotFoundException
    {
        em.remove(findItemById(id));
    }
    
    // - joined column queries -
    
    public List<ItemSerializable> getItemsOfCategory(Long categoryId)
    {
        return
            em.createQuery("SELECT new ItemSerializable(i) FROM Item i WHERE :catId IN i.categories", ItemSerializable.class)
              .setParameter("catId", categoryId)
              .getResultList();
    }
    
    public List<ItemSerializable> getItemsByCreator(Long creatorId)
    {
        return
            em.createQuery("SELECT new ItemSerializable(i) FROM Item i WHERE :creatId IN i.creators", ItemSerializable.class)
              .setParameter("creatId", creatorId)
              .getResultList();
    }
    
    // -- helper methods --
    
    private Item findItemById(Long id) throws ItemNotFoundException
    {

       Item findMe = em.find(Item.class, id);
       
       if (findMe == null)
       {
           throw new ItemNotFoundException(id);
       }
       
       return findMe;
    }
    /*
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
    }*/
}
