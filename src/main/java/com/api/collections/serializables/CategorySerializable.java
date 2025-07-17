package com.api.collections.serializables;

import com.api.collections.entities.BaseEntity;
import com.api.collections.entities.CategoryEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategorySerializable extends EntitySerializable 
{
    private String name;
    
    public CategorySerializable(CategoryEntity category)
    {
        super(category.getId());
        name = category.getName();
    }
    
    @Override
    public void assignFromEntity(BaseEntity entity) 
    {
        setId(entity.getId());
        name = ((CategoryEntity)(entity)).getName();
    }

    @Override
    public CategoryEntity toEntity() 
    {
        return new CategoryEntity(getId(), name);
    }
    
}
