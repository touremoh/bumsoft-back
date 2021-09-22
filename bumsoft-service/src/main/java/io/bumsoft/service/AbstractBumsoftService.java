package io.bumsoft.service;

import io.bumsoft.dao.entity.BumsoftEntity;
import io.bumsoft.dao.repository.BumsoftRepository;
import io.bumsoft.dao.specifications.BumsoftQueryBuilder;
import io.bumsoft.dto.BumsoftDto;
import io.bumsoft.dto.response.ErrorResponse;
import io.bumsoft.exception.BumsoftException;
import io.bumsoft.mapper.AbstractObjectsMapper;
import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Slf4j
public abstract class AbstractBumsoftService<E extends BumsoftEntity, D extends BumsoftDto, ID> implements BumsoftService<D, ID> {

    private final BumsoftRepository<E, ID> repository;
    private final AbstractObjectsMapper<E, D> mapper;
    private final ValidationService<E> validationService;
    private final BumsoftQueryBuilder<E> queryBuilder;

    protected AbstractBumsoftService(BumsoftRepository<E, ID> repository, AbstractObjectsMapper<E, D> mapper, ValidationService<E> validationService, BumsoftQueryBuilder<E> queryBuilder) {
        this.repository = repository;
        this.mapper = mapper;
        this.validationService = validationService;
        this.queryBuilder = queryBuilder;
    }

    /**
     * Create a new entity
     *
     * @param dto to be created
     * @return entity if successfully created
     */
    @Override
    public Either<ErrorResponse, D> create(D dto) {
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
            log.error(e.getMessage());
            return Either.left(ErrorResponseService.creationFailed(e.getMessage()));
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
     * @param entity to be processed before create
     * @throws BumsoftException thrown input did not satisfy requirements
     */
    public void processBeforeCreate(E entity) throws BumsoftException {
        log.info("Process before create ");
    }

    /**
     * Additional process after the object has been persisted
     * @param entity possibly processed after update
     * @throws BumsoftException thrown when something went wrong
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
    public Either<ErrorResponse, D> find(ID id) {
        // Find the resource by its ID
        Optional<E> option = this.repository.findById(id);
        // Return the found object or the corresponding error
        return option.<Either<ErrorResponse, D>>map(e -> Either.right(mapper.toDto(e))).orElseGet(() -> Either.left(ErrorResponseService.invalidId(id)));
    }

    /**
     * Update an element in the table represented by the entity E
     *
     * @param id id of the resource to update
     * @param dto to be updated
     * @return the updated entity
     */
    @Override
    public Either<ErrorResponse, D> update(ID id, D dto) {
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
            // Log error message
            log.error("An error occurred while trying to update the object");
            // Return the error to the client
            return Either.left(ErrorResponseService.updateFailed(e.getMessage()));
        }
    }

    /**
     * Apply initial check before update
     * @param id id of the resource to validate
     * @throws BumsoftException thrown when something went wrong
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
     * @param entity resource to process before update
     * @throws BumsoftException thrown when something went wrong
     */
    public void processBeforeUpdate(ID id, E entity) throws BumsoftException {
        log.info("Process before update - ID " + id);
    }

    /**
     * Additional process after update
     * @param entity resource to process after update
     * @throws BumsoftException thrown when something went wrong
     */
    public void processAfterUpdate(ID id, E entity) throws BumsoftException {
        log.info("Process after update - ID " + id);
    }

    /**
     * Delete the element from the table
     *
     * @param id of the element to be deleted
     */
    @Override
    public Either<ErrorResponse, Boolean> delete(ID id) {
        log.info("Deleting object with ID: " + id);

        if (!this.repository.existsById(id)) {
            // log error
            log.error("Trying to delete an object with an invalid ID");
            // Return error response
            return Either.left(ErrorResponseService.invalidId(id));
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
     * @param entity resource to validate
     * @throws BumsoftException thrown when something went wrong
     */
    public void checkIfInputIsValid(E entity) throws BumsoftException {
        // Validate input
        Either<String, Boolean> results = this.validationService.validate(entity);
        if (results.isLeft()) {
            // get validation error message
            final String errors = results.getLeft();

            // add errors to the logs
            log.error("Invalid input - Error message is: " + errors);

            // propagate error
            throw new BumsoftException(errors);
        }
    }

    /**
     * Find a list of objects by criteria
     * @param criteria criteria to filter resources on
     * @return List of found resources
     */
    @Override
    public Either<ErrorResponse, List<D>> findAllByCriteria(Map<String, String> criteria) {
        // Build the query to be executed
        Specification<E> specification = this.createSpecification(criteria);
        // Find all
        List<E> results = this.repository.findAll(specification);
        // return errors to the client
        return !results.isEmpty() ? Either.right(results.stream().map(mapper::toDto).toList()) :
                                    Either.left(ErrorResponseService.resourceNotFound("No results found with criteria=" + criteria.toString()));
    }

    /**
     * Find one element by criteria
     * @param criteria is the query criteria
     * @return one element corresponding to the query criteria
     */
    @Override
    public Either<ErrorResponse, D> findOneByCriteria(Map<String, String> criteria) {
        // Build the query to be executed
        Specification<E> specification = this.createSpecification(criteria);
        // Find one element using criteria
        Optional<E> result = this.repository.findOne(specification);
        // return errors to the client
        return result.<Either<ErrorResponse, D>>map(res -> Either.right(mapper.toDto(res)))
                     .orElseGet(() -> Either.left(ErrorResponseService.resourceNotFound("No results found with criteria=" + criteria.toString())));
    }

    public Specification<E> createSpecification(Map<String, String> criteria) {
        return this.queryBuilder.buildQuerySpecifications(criteria);
    }
}
