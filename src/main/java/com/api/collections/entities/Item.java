package com.api.collections.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ITEMS")
@Getter
@Setter
@NoArgsConstructor
public final class Item implements BaseEntity
{ 
    public static final int MAX_NOTES_CHARS = 500;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_ID", nullable = false)
    private Long id;
    
    @Column(name = "ITEM_NAME", length = MAX_CHARS, nullable = false)
    private String name;
    
    @Column(name = "NOTES", length = MAX_NOTES_CHARS)
    private String notes;
    
    @Column(name = "ITEM_DATE") 
    private LocalDate date; // can denote date released, manufactured, published, etc.
    
    @Lob
    @Column(name = "IMAGE")
    @Basic(fetch = FetchType.LAZY)
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private byte[] image;
    
    @OrderBy("id ASC")
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    @Setter(AccessLevel.NONE)
    private List<Category> categories;
    
    @OrderBy("id ASC")
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "CREATOR_ID")
    @Setter(AccessLevel.NONE)
    private List<Creator> creators;
    
    public Item(Long id, String name, String notes, LocalDate date, byte [] image, 
            List<Category> categories, List<Creator> creators)
    {
        this.id = id;
        this.name = name;
        this.notes = notes;
        this.date = date;
        setImageBytes(image);
        this.categories = deepCopyList(categories);
        this.creators = deepCopyList(creators);
    }
    
    public Item(Long id)
    {
        this.id = id;
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
    
    // -- helper methods --
    
    private <T extends BaseEntity> List<T> deepCopyList(List<T> source)
    {
        ArrayList<T> target = new ArrayList<>();
        Collections.copy(target, source);
        return target;
    }
}
