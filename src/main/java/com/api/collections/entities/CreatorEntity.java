package com.api.collections.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CREATORS")
@Getter
@Setter
@NoArgsConstructor
public class CreatorEntity extends BaseEntity
{
    @Column(name = "CREATOR_NAME", nullable = false, unique = true, length = MAX_CHARS)
    private String name;
    
    @Column(name = "TITLE", nullable = false, length = MAX_CHARS)
    private String title; // band, author, director, publisher, etc...
}
