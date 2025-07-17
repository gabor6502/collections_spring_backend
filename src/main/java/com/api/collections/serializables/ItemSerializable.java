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
        this.id = id;
        this.name = name;
        this.notes = notes;
        setImageBytes(image);
    }
    
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
    
    protected static String enumValToString(Enum e)
    {
        String [] tokens = e.name().split("[_]+");
        String processedToken;
        String result = "";
        
        for (int i = 0; i < tokens.length; i++)
        {
            processedToken = tokens[i].charAt(0)+ tokens[i].substring(1, tokens[i].length()).toLowerCase();
            result += processedToken;
            
            if (i < tokens.length - 1)
            {
                result += " ";
            }
        }
        
        return result;
    }
    
    public static <T extends Enum> T stringToEnumVal(Class<Enum> enumClass, String val)
    {
        String processedString = val.toUpperCase().replace(' ', '_');
        
        try
        {
            return (T) Enum.valueOf(enumClass, processedString);
        }
        catch (IllegalArgumentException iae)
        {
            return null;
        }
    }
}
