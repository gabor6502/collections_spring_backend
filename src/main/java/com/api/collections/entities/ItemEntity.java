package com.api.collections.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.persistence.Basic;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ITEMS")
@Getter
@Setter
@NoArgsConstructor
public class ItemEntity extends BaseEntity
{ 
    public static final int MAX_NOTES_CHARS = 500;
    
    @Column(name = "NAME", length = MAX_CHARS, nullable = false)
    private String name;
    
    @Column(name = "NOTES", length = MAX_NOTES_CHARS)
    private String notes;
    
    @Column(name = "DATE") 
    private LocalDate date; // can denote date released, manufactured, published, etc.
    
    @Lob
    @Column(name = "IMAGE")
    @Basic(fetch = FetchType.LAZY)
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private byte[] image;
    
    public ItemEntity(Long id, String name, String notes, LocalDate date, byte [] image)
    {
        super(id);
        this.name = name;
        this.notes = notes;
        this.date = date;
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
