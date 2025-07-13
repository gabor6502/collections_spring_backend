package com.api.collections.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.FetchType;

import java.util.List;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "CDS")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@NoArgsConstructor
public class CDEntity extends ItemEntity
{
    public enum Genre
    {
        ROCK,
        INDUSTRIAL,
        METAL,
        POP,
        REGGAE
    }
    public enum RecordingType
    {
        ALBUM,
        COMPILATION,
        LIVE
    }
    
    @Column(name = "GENRE")
    @Enumerated(EnumType.STRING)
    private Genre genre;
    
    @Column(name = "RECORDING_TYPE")
    @Enumerated(EnumType.STRING)
    private RecordingType recordingType;
    
    @Column(name = "BAND", nullable = false)
    private String band;
    
    @Column(name = "RELEASE_DATE")
    private Date releaseDate;
    
    // don't use elem collection, let's make a new object and persist it a more effective way
    //https://thorben-janssen.com/hibernate-tips-elementcollection/
    /*
    @Column(name = "TRACKLIST", nullable = false)
    @ElementCollection(targetClass = String.class, fetch = FetchType.LAZY)
    private List<String> tracklist;*/
}
