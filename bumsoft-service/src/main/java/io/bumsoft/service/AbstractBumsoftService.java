package io.bumsoft.service;

import io.bumsoft.dao.entity.BumsoftEntity;
import io.bumsoft.dao.repository.BumsoftRepository;
import io.bumsoft.dto.BumsoftDto;
import io.bumsoft.exception.ResourceNotFoundException;
import io.bumsoft.mapper.AbstractObjectsMapper;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
public abstract class AbstractBumsoftService<E extends BumsoftEntity, R extends BumsoftRepository<E, Long>, D extends BumsoftDto>
                implements BumsoftService<D> {

    private R repository;
    private AbstractObjectsMapper<E, D> mapper;

    protected AbstractBumsoftService(R repository, AbstractObjectsMapper<E, D> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * {@inheritDoc}
     */
    public D read(long id) throws ResourceNotFoundException {
        return repository.findById(id).map(mapper::toDto).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    /**
     * {@inheritDoc}
     */
    public D create(D object) {
        return this.processAfterCreate(repository.save(this.processBeforeCreate(object)));
    }

    /**
     * This method is used to process the object before persisting it
     * @param object to be process before persistence
     * @return the entity to be persisted
     */
    public abstract E processBeforeCreate(D object);

    /**
     * Process after the object is persisted
     * @param entity persisted object
     * @return void
     */
    public abstract D processAfterCreate(E entity);


    /**
     * {@inheritDoc}
     */
    public D update(D object) {
        return null;
    }



    /**
     * {@inheritDoc}
     */
    public void delete(long id) {
        log.info("Deleting object with ID " + id);
    }
}
