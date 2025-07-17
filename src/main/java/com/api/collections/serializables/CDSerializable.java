package com.api.collections.serializables;

import com.api.collections.entities.CDEntity;
import com.api.collections.entities.CDEntity.Genre;
import com.api.collections.entities.CDEntity.RecordingType;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CDSerializable extends ItemSerializable
{
    private Genre genre;
    private RecordingType recordingType;
    private String band;
    private LocalDate releaseDate;
    
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private List<String> tracklist;
    
    public CDSerializable(CDEntity cd)
    {
        super(cd.getId(), cd.getName(), cd.getNotes(), cd.getImageBytes());
        this.genre = cd.getGenre();
        this.recordingType = cd.getRecordingType();
        this.band = cd.getBand();
        this.releaseDate = cd.getReleaseDate();
        this.tracklist = cd.getTracklist(); 
    }
    
    public CDSerializable(Long id, String name, String notes, byte[] image,
            Genre genre, RecordingType recordingType, String band, LocalDate releaseDate,
            List<String> tracklist)
    {
       super(id, name, notes, image);
       this.genre = genre;
       this.recordingType = recordingType;
       this.band = band;
       this.releaseDate = releaseDate; // LocalDates are immutable so this is okay
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
    
    @Override
    public CDEntity toEntity()
    {
        return new CDEntity(getId(), getName(), getNotes(), getImageBytes(), genre, recordingType, band, releaseDate, tracklist);
    }
}
