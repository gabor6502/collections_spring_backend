package com.api.collections.entities;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Lob;
import jakarta.persistence.Basic;
import jakarta.persistence.FetchType;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public abstract class ItemEntity 
{ 
    public static final int MAX_CHARS = 256;
    public static final int MAX_NOTES_CHARS = 500;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @Setter(AccessLevel.NONE)
    private Long id;
    
    @Column(name = "NAME", length = MAX_CHARS, nullable = false)
    private String name;
    
    @Column(name = "DATE_AQUIRED")
    private Date dateAquired;
    
    @Column(name = "NOTES", length = MAX_NOTES_CHARS)
    private String notes;
    
    @Lob
    @Column(name = "IMAGE")
    @Basic(fetch = FetchType.LAZY)
    private byte[] image;
}
