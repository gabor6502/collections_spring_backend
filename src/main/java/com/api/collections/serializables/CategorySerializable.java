package com.api.collections.serializables;

import com.api.collections.entities.BaseEntity;
import com.api.collections.entities.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategorySerializable extends EntitySerializable 
{
    private String name;
    
    public CategorySerializable(Category category)
    {
        assignFromCategoryEntity(category);
    }
    
    private void assignFromCategoryEntity(Category category)
    {
        setId(category.getId());
        name = category.getName();
    }

    @Override
    public Category toEntity() 
    {
        return new Category(getId(), name);
    }
}
