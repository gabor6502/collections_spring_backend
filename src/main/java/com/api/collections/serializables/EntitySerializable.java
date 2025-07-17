package com.api.collections.serializables;

import com.api.collections.entities.BaseEntity;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class EntitySerializable implements Serializable
{
    private Long id;
    
    public abstract void assignFromEntity(BaseEntity entity);
    public abstract BaseEntity toEntity();
}
