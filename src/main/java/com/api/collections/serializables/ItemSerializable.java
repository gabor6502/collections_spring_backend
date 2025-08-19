package com.api.collections.serializables;

import com.api.collections.entities.BaseEntity;
import com.api.collections.entities.Category;
import com.api.collections.entities.Creator;
import com.api.collections.entities.Item;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;


@Getter
@Setter
@NoArgsConstructor
public class ItemSerializable extends EntitySerializable 
{
    private String name;
    private String notes;
    private LocalDate date;
    private byte [] image;
    
    private List<CategorySerializable> categories = new ArrayList<>();
    private List<CreatorSerializable> creators = new ArrayList<>();
    
    public ItemSerializable(Item item)
    {
        assignFromItemEntity(item);
    }

    private void assignFromItemEntity(Item item)
    {
        setId(item.getId());
        name = item.getName();
        notes = item.getNotes();
        date = item.getDate();
        image = item.getImageBytes();
        
        categories.clear();
        
        item.getCategories().forEach((cat) -> 
        {
            categories.add(new CategorySerializable(cat));
        });
        
        creators.clear();
        item.getCreators().forEach((creator) -> 
        {
            creators.add(new CreatorSerializable(creator));
        });
    }
   
    @Override
    public Item toEntity() 
    {
        ArrayList<Category> categories_ent = new ArrayList<>();
        ArrayList<Creator> creators_ent = new ArrayList<>();
        
        for (CategorySerializable c : categories)
        {
            categories_ent.add(new Category(c.getId(), c.getName()));
        }
        for (CreatorSerializable c : creators)
        {
            creators_ent.add(new Creator(c.getId(), c.getName(), c.getTitle()));
        }

        return new Item(getId(), name, notes, date, image, categories_ent, creators_ent);
    }

}
