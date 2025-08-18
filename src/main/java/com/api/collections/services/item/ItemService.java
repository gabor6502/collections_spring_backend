package com.api.collections.services.item;

import com.api.collections.entities.BaseEntity;
import com.api.collections.entities.Category;
import com.api.collections.entities.Creator;
import com.api.collections.entities.Item;
import com.api.collections.serializables.ItemSerializable;
import com.api.collections.services.exceptions.cannotInsert.CannotInsertItemException;
import com.api.collections.services.exceptions.notFound.ItemNotFoundException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

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
        try 
        {
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
    
    public List<ItemSerializable> getItemsOfUser()
    {
        // TODO need to link with user ID in User DB, but need to setup spring security for this
        return null; 
    }
    
    @Transactional
    public void updateItemData(ItemSerializable updated) throws ItemNotFoundException
    {

        // the item found by its ID will be tracked and persisted in the DB by the entity manager as we change it here
        Item item = findItemById(updated.getId());
        
        // update everything but the image, as we have a separate procedure for that and don't want to rewrite the bytes every time
        item.setName(updated.getName());
        item.setNotes(updated.getNotes());
        item.setDate(updated.getDate());
        
        // this could be setup differently: instead of adding all at once, compare differences and +/- 
        // especially if we're going to iterate over the both lists entirely anyway, will be less memory intensive
        /*
        // hard copy lists
        
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
        */
        
    }
    
    @Transactional
    public void updateItemImage(ItemSerializable updated) throws ItemNotFoundException
    {
        findItemById(updated.getId()).setImageBytes(updated.getImage());
    }
    
    @Transactional
    public void deleteItem(Long id) throws ItemNotFoundException
    {
        Item deleteMe = findItemById(id);
        
        deleteRelated(deleteMe.getCategories(), "categories");
        deleteRelated(deleteMe.getCreators(), "creators");

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
    
    private <T extends BaseEntity> void deleteRelated(List<T> entities, String listName)
    {
        // problem: I'd like to dynamically specify which list (creators or categories)
        //          that I'm picking from. Could just use string concatenation but that's sketchy...
        //
        // solution(s):
        // trying to use query building below, need to match a given id with the cb.in(item.get(listName)) predicate
        // -> need to figure out how to match a given creator/category id with the in() clause
            
        /*
        List<Item> inUse;
        
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Item> cq = cb.createQuery(Item.class);
        
        Root<Item> item = cq.from(Item.class);
        Predicate propertyInList = cb.in(item.get(listName));
        
        
        cb.all(sbqr)
        
        for (BaseEntity ent : entities)
        {
            // minimum two items using this property are the conditions for it remaining in DB
            
            // did someone say sql injection?
            //inUse = em.createQuery("SELECT i FROM Item i WHERE :creatId IN i."+listName+" LIMIT 2", Item.class)
            //    .setParameter("creatId", ent.getId())
            //    .getResultList();
            
           // inUse = em.createQuery(cb.in(item.get(listName)))setMaxResults(2).getResultList();
            
            // if only 1, it's the item to be deleted that is using it, thus we should delete property too
            if (inUse.size() == 1)
            {
                em.remove((T)ent);
            }
        }*/
        
    }
    
    private Item findItemById(Long id) throws ItemNotFoundException
    {

       Item findMe = em.find(Item.class, id);
       
       if (findMe == null)
       {
           throw new ItemNotFoundException(id);
       }
       
       return findMe;
    }
}
