package io.bumsoft.service;

import io.bumsoft.dao.entity.BumsoftEntity;
import io.bumsoft.dao.repository.BumsoftRepository;
import io.bumsoft.dto.BumsoftDto;
import io.bumsoft.exception.BumsoftException;
import io.bumsoft.mapper.AbstractObjectsMapper;
import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Slf4j
public abstract class AbstractBumsoftService<E extends BumsoftEntity, D extends BumsoftDto, M extends AbstractObjectsMapper<E, D>, ID, R extends BumsoftRepository<E, ID>>
                implements BumsoftService<D, ID> {

    private final R repository;
    private final M mapper;
    private final ValidationService<E> validationService;

    protected AbstractBumsoftService(R repository, M mapper, ValidationService<E> validationService) {
        this.repository = repository;
        this.mapper = mapper;
        this.validationService = validationService;
    }

    /**
     * Create a new entity
     *
     * @param dto to be created
     * @return entity if successfully created
     */
    @Override
    public Either<BumsoftException, D> create(D dto) {
        try {
            // Map the object to an entity type
            E entity = mapper.toEntity(dto);

            // Initial checks
            this.applyInitialCheckBeforeCreate(entity);

            // Process the object before create
            this.processBeforeCreate(entity);

            // Persist the object
            E result = this.repository.save(entity);

            // Process the object after create
            this.processAfterCreate(result);

            // Map to a Data Transfer Object type and returns the object
            return Either.right(mapper.toDto(result));
        } catch (BumsoftException e) {
            log.debug("An error occurred while trying to create the object");
            return Either.left(e);
        }
    }

    public void applyInitialCheckBeforeCreate(E entity) throws BumsoftException {
        // Validate object fields
        checkIfInputIsValid(entity);

        // Checking if the ID is null
        if (nonNull(entity.getId())) {
            log.error("The id "+entity.getId()+" is not allowed here");
            throw new BumsoftException("ID not allowed - The id should be null for create Operation");
        }
    }

    /**
     * Additional process before persisting the entity
     * @param entity
     * @throws BumsoftException
     */
    public void processBeforeCreate(E entity) throws BumsoftException {
        log.info("Process before create ");
    }

    /**
     * Additional process after the object has been persisted
     * @param entity
     * @throws BumsoftException
     */
    public void processAfterCreate(E entity) throws BumsoftException {
        log.info("Process after create ");
    }


    /**
     * Find an entity by its ID
     *
     * @param id of the element to be found
     * @return the entity
     */
    @Override
    public Either<BumsoftException, D> find(ID id) {
        Optional<E> option = this.repository.findById(id);
        return option.<Either<BumsoftException, D>>map(e -> Either.right(mapper.toDto(e)))
                     .orElseGet(() -> Either.left(new BumsoftException("Unable to find object with ID: " + id)));
    }

    /**
     * Update an element in the table represented by the entity E
     *
     * @param id
     * @param dto to be updated
     * @return the updated entity
     */
    @Override
    public Either<BumsoftException, D> update(ID id, D dto) {
        try {
            // Map the DTO with an Entity
            E entity = mapper.toEntity(dto);

            // Initial check before update
            this.applyInitialCheckBeforeUpdate(id);

            // Process before update
            this.processBeforeUpdate(id, entity);

            // Persisting the object
            E result = this.repository.save(entity);

            // Process after update
            this.processAfterUpdate(id, result);

            // map and return results
            return Either.right(mapper.toDto(result));
        } catch (BumsoftException e) {
            log.debug("An error occurred while trying to update the object");
            return Either.left(e);
        }
    }

    /**
     * Apply initial check before update
     * @param id
     * @throws BumsoftException
     */
    public void applyInitialCheckBeforeUpdate(ID id) throws BumsoftException {
        log.info("Initial check before persist");
        // Check if ID is null
        if (isNull(id)) {
            log.error("Invalid data: trying to update a resource with null id");
            throw new BumsoftException("Unable to update with null ID ");
        }
        // Check if object exists
        if (!this.repository.existsById(id)) {
            log.error("Invalid data: trying to update a resource that does not exists: " + id);
            throw new BumsoftException("Unable to update resource with invalid ID: " + id);
        }
    }

    /**
     * Additional process before update
     * @param entity
     * @throws BumsoftException
     */
    public void processBeforeUpdate(ID id, E entity) throws BumsoftException {
        log.info("Process before update");
    }

    /**
     * Additional process after update
     * @param entity
     * @throws BumsoftException
     */
    public void processAfterUpdate(ID id, E entity) throws BumsoftException {
        log.info("Process after update");
    }

    /**
     * Delete the element from the table
     *
     * @param id of the element to be deleted
     */
    @Override
    public Either<BumsoftException, Boolean> delete(ID id) {
        log.info("Deleting object with ID: " + id);
        if (!this.repository.existsById(id)) {
            log.error("Trying to delete an object with an invalid ID");
            return Either.left(new BumsoftException("The id of the object to delete is invalid ["+id+"]"));
        }

        this.repository.deleteById(id);

        if (!this.repository.existsById(id)) {
            log.info("Object with id "+id+" has been successfully deleted");
            return Either.right(Boolean.TRUE);
        }
        log.warn("Object with id "+id+" failed to be deleted");
        return Either.right(Boolean.FALSE);
    }

    /**
     * Check if the form is valid
     * @param entity
     * @throws BumsoftException
     */
    public void checkIfInputIsValid(E entity) throws BumsoftException {
        // Validate input
        Either<String, Boolean> results = this.validationService.validate(entity);
        if (results.isLeft()) {
            final String errors = results.getLeft();
            log.error("Invalid input - Error message is: " + errors);
            throw new BumsoftException(errors);
        }
    }
}
