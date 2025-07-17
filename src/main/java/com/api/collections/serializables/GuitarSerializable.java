package com.api.collections.serializables;

import com.api.collections.entities.GuitarEntity;
import com.api.collections.entities.GuitarEntity.GuitarType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GuitarSerializable extends ItemSerializable 
{
    
    private GuitarType type;
    private String make;
    private String model;
    private String serialNumber;
    private int strings;

    public GuitarSerializable(GuitarEntity guitar)
    {
        super(guitar);
        this.type = guitar.getType();
        this.make = guitar.getMake();
        this.model = guitar.getModel();
        this.serialNumber = guitar.getSerialNumber();
        this.strings = guitar.getStrings();
    }
    
    public GuitarSerializable(Long id, String name, String notes, byte[] image,
            GuitarType type, String make, String model, String serialNumber, int strings)
    {
        super(id, name, notes, image);
        this.type = type;
        this.make = make;
        this.model = model;
        this.serialNumber = serialNumber;
        this.strings = strings;
    }
    
    @Override
    public GuitarEntity toEntity()
    {
        return new GuitarEntity(getId(), getName(), getNotes(), getImageBytes(), type, make, model, serialNumber, strings);
    }

}
