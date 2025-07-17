package com.api.collections.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CATEGORIES")
@Getter
@Setter
@NoArgsConstructor
public class CategoryEntity extends BaseEntity
{
    // what category of thing is it? CD, video game, book, etc.
    
    @Column(name = "CATEGORY_NAME", nullable = false, unique = true, length = MAX_CHARS)
    private String name;
    
    public CategoryEntity(Long id, String name)
    {
        super(id);
        this.name = name;
    }
}
