package io.bumsoft.service;

import io.bumsoft.dao.entity.BumsoftEntity;
import io.bumsoft.dao.repository.BumsoftRepository;
import io.bumsoft.dto.BumsoftDto;
import io.bumsoft.exception.BumsoftException;
import io.bumsoft.mapper.AbstractObjectsMapper;
import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

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
        // Checking if the ID is null
        if (nonNull(entity.getId())) {
            log.error("The id "+entity.getId()+" is not allowed here");
            throw new BumsoftException("ID not allowed - The id should be null for create Operation");
        }

        // Validate object fields
        if (validationService.notValid(entity)) {
            final String message = validationService.getValidationErrorMessage(entity);
            log.error("Invalid input - Error message is: " + message);
            throw new BumsoftException(message);
        }
    }

    /**
     * Additional process before persisting the entity
     * @param entity
     * @throws BumsoftException
     */
    abstract void processBeforeCreate(E entity) throws BumsoftException;

    /**
     * Additional process after the object has been persisted
     * @param entity
     * @throws BumsoftException
     */
    abstract void processAfterCreate(E entity) throws BumsoftException;


    /**
     * Find an entity by its ID
     *
     * @param id of the element to be found
     * @return the entity
     */
    @Override
    public Either<BumsoftException, D> read(ID id) {
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
            log.info("Starting update process");
            if (!repository.existsById(id))
                throw new BumsoftException("Trying to update a row with an invalid ID: " + id);

            // Map the DTO with an Entity
            E entity = mapper.toEntity(dto);

            log.info("Process before update");
            this.processBeforeUpdate(id, entity);

            log.info("Persisting the object");
            E result = this.repository.save(entity);

            log.info("Process after update");
            this.processAfterUpdate(id, result);

            log.info("Object successfully updated");
            return Either.right(mapper.toDto(result));
        } catch (BumsoftException e) {
            log.debug("An error occurred while trying to update the object");
            return Either.left(e);
        }
    }

    /**
     * Additional process before update
     * @param entity
     * @throws BumsoftException
     */
    abstract void processBeforeUpdate(ID id, E entity) throws BumsoftException;

    /**
     * Additional process after update
     * @param entity
     * @throws BumsoftException
     */
    abstract void processAfterUpdate(ID id, E entity) throws BumsoftException;

    /**
     * Delete the element from the table
     *
     * @param id of the element to be deleted
     */
    @Override
    public void delete(ID id) {
        log.info("Deleting object with ID: " + id);
        this.repository.deleteById(id);
        log.info("Object deleted");
    }
}
