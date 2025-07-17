package com.api.collections.serializables;

import com.api.collections.entities.CDEntity;

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
    private String genre;
    private String recordingType;
    private String band;
    private LocalDate releaseDate;
    
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private List<String> tracklist = new ArrayList<String>();
    
    public CDSerializable(CDEntity cd)
    {
        super(cd.getId(), cd.getName(), cd.getNotes(), cd.getImageBytes());
        this.genre = enumValToString(cd.getGenre());
        this.recordingType = enumValToString(cd.getRecordingType());
        this.band = cd.getBand();
        this.releaseDate = cd.getReleaseDate().toLocalDate();
    }
    
    public CDSerializable(Long id, String name, String notes, byte[] image,
            String genre, String recordingType, String band, LocalDate releaseDate)
    {
       super(id, name, notes, image);
       this.genre = genre;
       this.recordingType = recordingType;
       this.band = band;
       this.releaseDate = releaseDate; // LocalDates are immutable so this is okay
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
