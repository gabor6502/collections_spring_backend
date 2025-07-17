package com.api.collections.serializables;

import com.api.collections.entities.BaseEntity;
import com.api.collections.entities.CreatorEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreatorSerializable extends EntitySerializable
{
    private String name;
    private String title;
    
    public CreatorSerializable(CreatorEntity creator)
    {
        assignFromCreatorEntity(creator);
    }
    
    private void assignFromCreatorEntity(CreatorEntity creator)
    {
        setId(creator.getId());
        name = creator.getName();
        title = creator.getTitle();
    }
    
    @Override
    public void assignFromEntity(BaseEntity entity) {
        assignFromCreatorEntity((CreatorEntity)entity);
    }

    @Override
    public BaseEntity toEntity() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
