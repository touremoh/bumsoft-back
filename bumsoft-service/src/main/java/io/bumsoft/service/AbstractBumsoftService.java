package io.bumsoft.service;

import io.bumsoft.dao.entity.BumsoftEntity;
import io.bumsoft.dao.repository.BumsoftRepository;
import io.bumsoft.exception.ResourceNotFoundException;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;

@NoArgsConstructor
public abstract class AbstractBumsoftService<E extends BumsoftEntity, R extends BumsoftRepository<E, Long>> {

    private R repository;

    public AbstractBumsoftService(R repository) {
        this.repository = repository;
    }

    public E findById(@PathVariable Long id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }
}
