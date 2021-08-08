package io.bumsoft.service;

import io.bumsoft.dao.entity.BumsoftEntity;
import io.bumsoft.dto.BumsoftDto;
import io.bumsoft.dto.BumsoftResponse;
import io.bumsoft.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;

public interface BumsoftService<D extends BumsoftDto> {

    /**
     * Create a new entity
     * @param object to be created
     * @return entity if successfully created
     */
    D create(D object);

    /**
     * Find an entity by its ID
     * @param id of the element to be found
     * @return the entity
     */
    D read(long id) throws ResourceNotFoundException;

    /**
     * Update an element in the table represented by the entity E
     * @param object to be updated
     * @return the updated entity
     */
    D update(D object);

    /**
     * Delete the element from the table
     * @param id of the element to be deleted
     * @return nothing
     */
    void delete(long id);
}
