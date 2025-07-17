package com.api.collections.serializables;

import com.api.collections.entities.BaseEntity;
import com.api.collections.entities.ItemEntity;

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
    
    public ItemSerializable(ItemEntity item)
    {
        assignFromItemEntity(item);
    }

    private void assignFromItemEntity(ItemEntity item)
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
        assignFromItemEntity((ItemEntity)entity);
    }

    @Override
    public ItemEntity toEntity() 
    {
        return new ItemEntity(getId(), name, notes, date, image);
    }
    
}
