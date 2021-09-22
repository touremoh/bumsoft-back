package io.bumsoft.service;

import io.bumsoft.dto.BumsoftDto;
import io.bumsoft.dto.response.ErrorResponse;
import io.vavr.control.Either;

import java.util.List;
import java.util.Map;

public interface BumsoftService<D extends BumsoftDto, ID> {

    /**
     * Create a new entity
     * @param dto to be created
     * @return dto if successfully created
     */
    Either<ErrorResponse, D> create(D dto);

    /**
     * Find an entity by its ID
     * @param id of the element to be found
     * @return the dto
     */
    Either<ErrorResponse, D> find(ID id);

    /**
     * Update an element in the table represented by the entity E
     * @param id of the element to be updated
     * @param dto to be updated
     * @return the updated entity
     */
    Either<ErrorResponse, D> update(ID id, D dto);

    /**
     * Delete the element from the table
     * @param id of the element to be deleted
     */
    Either<ErrorResponse, Boolean> delete(ID id);

    /**
     * Find a list of objects by criteria
     * @param criteria criteria of the query
     * @return List of DTO
     */
    Either<ErrorResponse, List<D>> findAllByCriteria(Map<String, String> criteria);

    /**
     * Find one element by criteria
     * @param criteria is the query criteria
     * @return one element corresponding to the query criteria
     */
    Either<ErrorResponse, D> findOneByCriteria(Map<String, String> criteria);
}
