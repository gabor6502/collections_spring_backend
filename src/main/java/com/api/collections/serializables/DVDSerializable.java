package com.api.collections.serializables;

import com.api.collections.entities.DVDEntity;
import com.api.collections.entities.DVDEntity.EntertainmentType;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DVDSerializable extends ItemSerializable
{
    private EntertainmentType media;
    private int runtime;
    private LocalDate releaseDate; // switch from sql.Date in entity
    
    public DVDSerializable(DVDEntity dvd)
    {
        super(dvd.getId(), dvd.getName(), dvd.getNotes(), dvd.getImageBytes());
        this.media = dvd.getMedia();
        this.runtime = dvd.getRuntime();      
        this.releaseDate = dvd.getReleaseDate();
    }  
    
    public DVDSerializable(Long id, String name, String notes, byte[] image,
            EntertainmentType media, int runtime, LocalDate releaseDate)
    {
        super(id, name, notes, image);
        this.media = media;
        this.runtime = runtime;
        this.releaseDate = releaseDate; // LocalDates are immutable, so this is okay
    }
    
    @Override
    public DVDEntity toEntity()
    {
        return new DVDEntity(getId(), getName(), getNotes(), getImageBytes(), media, runtime, releaseDate);
    }
    
}
