package com.api.collections.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "GAMES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@NoArgsConstructor
public class GameEntity extends ItemEntity
{
    public enum Medium // the medium it exists in, IRL
    {
        CARTRIDGE,
        DISC
    }
    
    @Column(name = "MEDIUM")
    @Enumerated(EnumType.STRING)
    private Medium medium;
    
    @Column(name = "CONSOLE", nullable = false)
    private String console;
  
    @Column(name = "RELEASE_YEAR")
    private int releaseYear;
}
