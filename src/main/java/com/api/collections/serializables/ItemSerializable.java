package com.api.collections.serializables;

import com.api.collections.entities.ItemEntity;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.AccessLevel;

@Getter
@Setter
public abstract class ItemSerializable implements Serializable
{   
    private String name;
    private String notes;
    
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private byte[] image;
    
    @Setter(AccessLevel.NONE)
    private Long id;
    
    public ItemSerializable(ItemEntity item)
    {
        id = item.getId();
        name = item.getName();
        notes = item.getNotes();
        image = item.getImageBytes();
    }
    
    public ItemSerializable(Long id, String name, String notes, byte[] image)
    {
        this.id = id == null ? -1l: id;
        this.name = name;
        this.notes = notes;
        setImageBytes(image);
    }
    
    public abstract ItemEntity toEntity();
    
    public byte[] getImageBytes()
    {
        byte[] image_copy = new byte[image.length];
        
        System.arraycopy(image, 0, image_copy, 0, image.length);
        
        return image_copy;
    }
    
    public final void setImageBytes(byte[] bytes)
    {
        image = new byte[bytes.length]; // new set of bytes (other gets garbage collected)
        
        System.arraycopy(bytes, 0, image, 0, bytes.length);
    }

}
