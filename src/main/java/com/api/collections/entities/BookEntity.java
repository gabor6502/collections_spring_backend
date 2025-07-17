package com.api.collections.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "BOOKS")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@NoArgsConstructor
public class BookEntity extends ItemEntity 
{
    public enum Genre
    {
        NON_FICTION,
        BIOGRAPHY,
        HISTORY,
        SCIENCE_FICTION,
        ADVENTURE,
        FANTASY,
        FICTION
    }
    
    @Column(name = "GENRE")
    @Enumerated(EnumType.STRING)
    private Genre genre;
    
    @Column(name = "AUTHOR")
    private String author;
    
    @Column(name = "DATE_PUBLISHED")
    private Date datePublished;
}
