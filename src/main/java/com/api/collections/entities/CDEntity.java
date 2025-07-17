package com.api.collections.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.time.LocalDate;

import lombok.AccessLevel;
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
    private LocalDate releaseDate;
    
    @Column(name = "TRACKLIST", nullable = false)
    @OneToMany(fetch = FetchType.EAGER)
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private List<String> tracklist = new ArrayList<String>();
    
    public CDEntity(Long id, String name, String notes, byte [] image,
            Genre genre, RecordingType recordingType, String band, LocalDate releaseDate,
            List<String> tracklist)
    {
        super(id, name, notes, image);
        this.genre = genre;
        this.recordingType = recordingType;
        this.band = band;
        this.releaseDate = releaseDate;
        
        Collections.copy(this.tracklist, tracklist);
    }
    
    public List<String> getTracklist()
    {
        ArrayList copy = new ArrayList<String>();
        Collections.copy(copy, tracklist);
        
        return copy;
    }
    
    public void setTracklist(List<String> source)
    {
        Collections.copy(tracklist, source);
    }
}
