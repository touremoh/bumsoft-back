package io.bumsoft.service;

import io.bumsoft.dao.entity.BumsoftEntity;
import io.bumsoft.exception.ResourceNotFoundException;

/**
 *
 * @param <E> the ype of entity we are trying to find
 * @param <T> the parameter we are giving to the service for the resource
 */
public interface BumsoftService<E extends BumsoftEntity, T> {
    E findById(T id) throws ResourceNotFoundException;
}
