package io.bumsoft.service;

import io.bumsoft.dto.BumsoftDto;

import io.bumsoft.exception.BumsoftException;
import io.vavr.control.Either;

public interface BumsoftService<D extends BumsoftDto, ID> {

    /**
     * Create a new entity
     * @param dto to be created
     * @return dto if successfully created
     */
    Either<BumsoftException, D> create(D dto);

    /**
     * Find an entity by its ID
     * @param id of the element to be found
     * @return the dto
     */
    Either<BumsoftException, D> find(ID id);

    /**
     * Update an element in the table represented by the entity E
     * @param id of the element to be updated
     * @param dto to be updated
     * @return the updated entity
     */
    Either<BumsoftException, D> update(ID id, D dto);

    /**
     * Delete the element from the table
     * @param id of the element to be deleted
     */
    Either<BumsoftException, Boolean> delete(ID id);
}
