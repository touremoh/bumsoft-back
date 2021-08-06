package io.bumsoft.service;

import io.bumsoft.dao.entity.BumsoftEntity;
import io.bumsoft.dto.BumsoftResponse;
import org.springframework.http.ResponseEntity;

public interface BumsoftService<E extends BumsoftEntity> {

    /**
     * Create a new entity
     * @param entity to be created
     * @return entity if successfully created
     */
    ResponseEntity<BumsoftResponse> create(E entity);

    /**
     * Find an entity by its ID
     * @param id of the element to be found
     * @return the entity
     */
    ResponseEntity<BumsoftResponse> read(Long id);

    /**
     * Update an element in the table represented by the entity E
     * @param id of the element to be updated
     * @param entity to be updated
     * @return the updated entity
     */
    ResponseEntity<BumsoftResponse> update(Long id, E entity);

    /**
     * Delete the element from the table
     * @param id of the element to be deleted
     * @return nothing
     */
    ResponseEntity<Void> delete(Long id);
}
