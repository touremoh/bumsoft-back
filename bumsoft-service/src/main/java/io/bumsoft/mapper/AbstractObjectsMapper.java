package io.bumsoft.mapper;

import io.bumsoft.dao.entity.BumsoftEntity;
import io.bumsoft.dto.BumsoftDto;
import io.bumsoft.dto.BumsoftResponse;

public interface AbstractObjectsMapper<E extends BumsoftEntity,D extends BumsoftDto> {

    /**
     * Maps an object received from the front to an entity
     * @param dto received from the front
     * @return entity mapping the dto
     */
    E toEntity(D dto);

    /**
     * Maps an entity to an object to be exposed by the service
     * @param entity
     * @return the dto mapping the entity
     */
    D toDto(E entity);
}
