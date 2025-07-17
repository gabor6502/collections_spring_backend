package com.api.collections.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "DVDS")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@NoArgsConstructor
public class DVDEntity extends ItemEntity
{
    public enum EntertainmentType
    {
        TELEVISION,
        MOVIE
    }
    
    @Column(name = "MEDIA")
    @Enumerated(EnumType.STRING)
    private EntertainmentType media;
    
    @Column(name = "RUNTIME")
    private int runtime; // in minutes
    
    @Column(name = "RELEASE_DATE")
    private LocalDate releaseDate;
    
    public DVDEntity(Long id, String name, String notes, byte [] image,
            EntertainmentType media, int runtime, LocalDate releaseDate)
    {
        super(id, name, notes, image);
        this.media = media;
        this.runtime = runtime;
        this.releaseDate = releaseDate;
    }
}
