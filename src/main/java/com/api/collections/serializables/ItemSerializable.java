package com.api.collections.serializables;

import com.api.collections.entities.ItemEntity;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    
    public byte[] getImageBytes()
    {
        byte[] image_copy = new byte[image.length];
        
        System.arraycopy(image, 0, image_copy, 0, image.length);
        
        return image_copy;
    }
    
    public void setImageBytes(byte[] bytes)
    {
        image = new byte[bytes.length]; // new set of bytes (other gets garbage collected)
        
        System.arraycopy(bytes, 0, image, 0, bytes.length);
    }
}
