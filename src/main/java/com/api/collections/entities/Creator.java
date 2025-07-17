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
@Table(name = "CREATORS")
@Getter
@Setter
@NoArgsConstructor
public class Creator implements BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CREATOR_ID", nullable = false)
    private Long id;
    
    @Column(name = "CREATOR_NAME", nullable = false, unique = true, length = MAX_CHARS)
    private String name;
    
    @Column(name = "CREATOR_TITLE", nullable = false, length = MAX_CHARS)
    private String title; // band, author, director, publisher, etc...
    
    public Creator(Long id, String name, String title)
    {
        this.id = id;
        this.name = name;
        this.title = title;
    }
}
