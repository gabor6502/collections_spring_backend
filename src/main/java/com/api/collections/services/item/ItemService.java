package com.api.collections.services.item;

import com.api.collections.entities.BaseEntity;
import com.api.collections.entities.Category;
import com.api.collections.entities.Creator;
import com.api.collections.entities.Item;
import com.api.collections.serializables.EntitySerializable;
import com.api.collections.serializables.ItemSerializable;
import com.api.collections.services.exceptions.BadIdException;
import com.api.collections.services.exceptions.NoCategoriesSuppliedException;
import com.api.collections.services.exceptions.NoCreatorsSuppliedException;
import com.api.collections.services.exceptions.NoRelevantQueryException;
import com.api.collections.services.exceptions.NullSerializedException;
import com.api.collections.services.exceptions.cannotInsert.CannotInsertItemException;
import com.api.collections.services.exceptions.notFound.ItemNotFoundException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
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
    public ItemSerializable create(ItemSerializable addMe) throws CannotInsertItemException, NoCategoriesSuppliedException, NoCreatorsSuppliedException, NullSerializedException, BadIdException
    {
        Item toAdd = entityValidationAdapter(addMe);
        
        try 
        {
            em.persist(toAdd);
        } catch (IllegalArgumentException iae)
        {
            throw new CannotInsertItemException();
        }
        
        return new ItemSerializable(toAdd);
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
    public ItemSerializable updateItemData(ItemSerializable updated) throws ItemNotFoundException, NoCategoriesSuppliedException, NoCreatorsSuppliedException, NullSerializedException, BadIdException
    {
        entityValidationAdapter(updated);
        
        // the item found by its ID will be tracked and persisted in the DB by the entity manager as we change it here
        Item item = findItemById(updated.getId());
        
        // update everything but the image, as we have a separate procedure for that and don't want to rewrite the bytes every time
        item.setName(updated.getName());
        item.setNotes(updated.getNotes());
        item.setDate(updated.getDate());
        
        resolveListDiff(item.getCategories(), updated.getCategories());
        resolveListDiff(item.getCreators(), updated.getCreators());
        
        return new ItemSerializable(item);
    }
    
    @Transactional
    public ItemSerializable updateItemImage(ItemSerializable updated) throws ItemNotFoundException
    {
        Item updateMe = findItemById(updated.getId());
        
        updateMe.setImageBytes(updated.getImage());
        
        return new ItemSerializable(updateMe);
    }
    
    @Transactional
    public void deleteItem(Long id) throws ItemNotFoundException
    {
        Item deleteMe = findItemById(id);

        deleteRelated(deleteMe.getCategories());
        deleteRelated(deleteMe.getCategories());

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

    /**
     * entityValidationAdapter
     * 
     * Handles business logic related validation and returns an entity from the serialized form.
     * Given non null ItemSerializable, makes sure lists are non-empty and ID is non-negative.
     */
    private Item entityValidationAdapter(ItemSerializable ser) throws NullSerializedException, NoCategoriesSuppliedException, NoCreatorsSuppliedException, BadIdException
    {
        if (ser == null)
        {
            throw new NullSerializedException();
        }
        
        Item fromSer = ser.toEntity();
        
        if (fromSer.getId() == null || fromSer.getId() < 0)
        {
            throw new BadIdException();
        }
        else if (fromSer.getCategories().isEmpty())
        {
            throw new NoCategoriesSuppliedException();
        }
        else if (fromSer.getCreators().isEmpty())
        {
            throw new NoCreatorsSuppliedException();
        }
     
        return fromSer;
    }
    
    private void validateSerialized(ItemSerializable ser)
    {
        
    }
    
    private <T extends BaseEntity> void deleteRelated(List<T> entities)
    {
        try
        {
            _deleteRelated(entities);
        } catch (NoRelevantQueryException nrqe)
        {
            // should probably get a logger going...
            System.out.println(nrqe);
        }
    }
    
    private <T extends BaseEntity> void _deleteRelated(List<T> entities) throws NoRelevantQueryException
    {
        List<Item> inUse;
        TypedQuery tq;
        
        // not the most elegant solution to dynamically select which list we're checking, but its the safest in the meanwhile
        if (entities.get(0) instanceof Category)
        {
            tq = em.createQuery("SELECT i FROM Item i WHERE :creatId IN i.categories LIMIT 2", Item.class); 
        }     
        else if (entities.get(0) instanceof Creator)
        {
            tq = em.createQuery("SELECT i FROM Item i WHERE :creatId IN i.creators LIMIT 2", Item.class);
        }
        else
        {
            throw new NoRelevantQueryException(entities.get(0).getClass());
        }
        
        for (BaseEntity ent : entities)
        {
            inUse = tq.setParameter("creatId", ent.getId()).getResultList();

            // minimum two items using this property are the conditions for it remaining in DB
            // if only 1, it's the item to be deleted that is using it, thus we should delete property too
            if (inUse.size() == 1)
            {
                em.remove((T)ent);
            }
        }
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
    
    /**
     * resolveListDiff
     * 
     * The algorithm works by comparing the IDs of entities at a given 
     * index/frame, i:
     * (Given that both arrays are sorted in ascending order)
     *  - If original[i].id == changes[i].id
     *      Go to next index, as these are equivalent entities.
     *  - Else if the id from original[i] is less than the id of changes[i]
     *      Then remove the item at original[i].
     *  - Else if the id from original[i] is greater than the id of changes[i]
     *      Then add changes[i] at index i to original, and go to next frame 
     *      (i++).
     * 
     * An edge case is when the original list becomes or just is smaller than 
     * the changes list. In this case, when we access the out of bounds index
     * in original, highest value long is returned as the id of original[i].
     * This has the effect of adding in whatever is at changes[i] into 
     * original[i].
     * 
     */
    private static <T extends BaseEntity, U extends EntitySerializable> 
        void resolveListDiff(List<T> original, List<U> changes)
    {
        if (original == null || changes == null)
        {
            return;
        }
        
        Long ido, idc; // id of current element in original, changes
        int i = 0; // index in original and changes (looking at a frame of each)
        while (i < changes.size())
        {
            ido = i < original.size() ? original.get(i).getId() : Long.MAX_VALUE;
            idc = changes.get(i).getId();
            
            if (ido.equals(idc))
            {
                i++; // equivalent, so move on
            }
            else if (ido < idc)
            {
                original.remove(i);
            }
            else if (ido > idc)
            {
                original.add(i, (T)(changes.get(i).toEntity()));
                i++;
            }
        }
    }
        
}
