package com.api.collections.entities;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Lob;
import jakarta.persistence.Basic;
import jakarta.persistence.FetchType;

import lombok.Getter;
import lombok.Setter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public abstract class ItemEntity 
{ 
    public static final int MAX_CHARS = 256;
    public static final int MAX_NOTES_CHARS = 500;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @Setter(AccessLevel.NONE)
    private Long id;
    
    @Column(name = "NAME", length = MAX_CHARS, nullable = false)
    private String name;
    
    @Column(name = "NOTES", length = MAX_NOTES_CHARS)
    private String notes;
    
    @Lob
    @Column(name = "IMAGE")
    @Basic(fetch = FetchType.LAZY)
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private byte[] image;
    
    public ItemEntity(String name, String notes, byte [] image)
    {
        id = -1l;
        this.name = name;
        this.notes = notes;
        System.arraycopy(image, 0, this.image, 0, image.length);
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
