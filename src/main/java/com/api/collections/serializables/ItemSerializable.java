package com.api.collections.serializables;

import com.api.collections.entities.BaseEntity;
import com.api.collections.entities.Item;

import java.time.LocalDate;

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
    }
    
    @Override
    public void assignFromEntity(BaseEntity entity) 
    {
        assignFromItemEntity((Item)entity);
    }

    @Override
    public Item toEntity() 
    {
        return new Item(getId(), name, notes, date, image);
    }
    
}
