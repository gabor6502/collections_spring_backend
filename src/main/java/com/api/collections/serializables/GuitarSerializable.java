package com.api.collections.serializables;

import com.api.collections.entities.GuitarEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GuitarSerializable extends ItemSerializable 
{
    
    private String type;
    private String make;
    private String model;
    private String serialNumber;
    private int strings;

    public GuitarSerializable(GuitarEntity guitar)
    {
        super(guitar);
        this.type = enumValToString(guitar.getType());
        this.make = guitar.getMake();
        this.model = guitar.getModel();
        this.serialNumber = guitar.getSerialNumber();
        this.strings = guitar.getStrings();
    }
    
    public GuitarSerializable(Long id, String name, String notes, byte[] image,
            String type, String make, String model, String serialNumber, int strings)
    {
        super(id, name, notes, image);
        this.type = type;
        this.make = make;
        this.model = model;
        this.serialNumber = serialNumber;
        this.strings = strings;
    }
    
}
