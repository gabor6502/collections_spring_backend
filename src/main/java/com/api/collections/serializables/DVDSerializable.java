package com.api.collections.serializables;

import com.api.collections.entities.DVDEntity;
import com.api.collections.entities.DVDEntity.EntertainmentType;

import java.time.LocalDate;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class DVDSerializable extends ItemSerializable
{
    private EntertainmentType media;
    private int runtime;
    
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private LocalDate releaseDate; // switch from sql.Date in entity
    
    public DVDSerializable(Long id, String name, String notes, byte[] image,
            EntertainmentType media, int runtime, LocalDate releaseDate)
    {
        super(id, name, notes, image);
        this.media = media;
        this.runtime = runtime;
        //https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html
       // this.releaseDate = LocalDate.of(releaseDate.);
    }
    
    public DVDSerializable(DVDEntity dvd)
    {
        
    }
    
    public LocalDate getReleaseDate()
    {
        
    }
    
    public void setReleaseDate()
    {
        
    }
}
