package com.api.collections.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CATEGORIES")
@Getter
@Setter
@NoArgsConstructor
public class Category implements BaseEntity
{
    // what category of thing is it? CD, video game, book, etc.
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_ID", nullable = false)
    private Long id;
    
    @Column(name = "CATEGORY_NAME", nullable = false, unique = true, length = MAX_CHARS)
    private String name;
    
    public Category(Long id, String name)
    {
        this.id = id;
        this.name = name;
    }
}
