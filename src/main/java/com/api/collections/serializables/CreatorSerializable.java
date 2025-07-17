package com.api.collections.serializables;

import com.api.collections.entities.BaseEntity;
import com.api.collections.entities.Creator;

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
    
    public CreatorSerializable(Creator creator)
    {
        assignFromCreatorEntity(creator);
    }
    
    private void assignFromCreatorEntity(Creator creator)
    {
        setId(creator.getId());
        name = creator.getName();
        title = creator.getTitle();
    }
    
    @Override
    public void assignFromEntity(BaseEntity entity) {
        assignFromCreatorEntity((Creator)entity);
    }

    @Override
    public Creator toEntity() {
        return new Creator(getId(), name, title);
    }
    
}
