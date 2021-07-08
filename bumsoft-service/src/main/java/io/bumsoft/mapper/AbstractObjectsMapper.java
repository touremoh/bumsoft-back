package io.bumsoft.mapper;

import io.bumsoft.dao.entity.BumsoftEntity;
import io.bumsoft.dto.BumsoftResponse;

public interface AbstractObjectsMapper<E extends BumsoftEntity,T extends BumsoftResponse> {

    /**
     * Maps an object received from the front to an entity
     * @param dto received from the front
     * @return entity mapping the dto
     */
    E toEntity(T dto);

    /**
     * Maps an entity to an object to be exposed by the service
     * @param entity
     * @return the dto mapping the entity
     */
    T toDto(E entity);
}
