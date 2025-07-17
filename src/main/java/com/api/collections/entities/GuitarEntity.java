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
@Table (name = "GUITARS")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@NoArgsConstructor
public class GuitarEntity extends ItemEntity
{
    public enum GuitarType 
    {
        ELECTRIC_GUITAR,
        ELECTRIC_BASS,
        ACCOUSTIC_GUITAR
    }
    
    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private GuitarType type;
    
    @Column(name = "MAKE", length = MAX_CHARS, nullable = false)
    private String make;
    
    @Column(name = "MODEL", length = MAX_CHARS, nullable = false)
    private String model;
    
    @Column(name = "SERIAL_NUMBER", length = MAX_CHARS)
    private String serialNumber; // often has letters
    
    @Column(name = "STRINGS")
    private int strings;

    public GuitarEntity(Long id, String name, String notes, byte [] image,
            GuitarType type, String make, String model, String serialNumber, int strings)
    {
        super(id, name, notes, image);
        this.type = type;
        this.make = make;
        this.model = model;
        this.serialNumber = serialNumber;
        this.strings = strings;
    }
}
