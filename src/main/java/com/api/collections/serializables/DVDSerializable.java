package com.api.collections.serializables;

import com.api.collections.entities.DVDEntity;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DVDSerializable extends ItemSerializable
{
    private String media;
    private int runtime;
    private LocalDate releaseDate; // switch from sql.Date in entity
    
    public DVDSerializable(DVDEntity dvd)
    {
        super(dvd.getId(), dvd.getName(), dvd.getNotes(), dvd.getImageBytes());
        this.media = enumValToString(dvd.getMedia());
        this.runtime = dvd.getRuntime();      
        this.releaseDate = dvd.getReleaseDate().toLocalDate();
    }  
    
    public DVDSerializable(Long id, String name, String notes, byte[] image,
            String media, int runtime, LocalDate releaseDate)
    {
        super(id, name, notes, image);
        this.media = media;
        this.runtime = runtime;
        this.releaseDate = releaseDate; // LocalDates are immutable, so this is okay
    }
    
}
