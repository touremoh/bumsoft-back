package io.bumsoft.service;

import io.bumsoft.constants.BumsoftResponseCode;
import io.bumsoft.dao.entity.BumsoftEntity;
import io.bumsoft.dao.repository.BumsoftRepository;
import io.bumsoft.dto.BumsoftResponse;
import io.bumsoft.dto.common.ErrorResponse;
import io.bumsoft.mapper.AbstractObjectsMapper;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@Slf4j
@NoArgsConstructor
public abstract class AbstractBumsoftService<E extends BumsoftEntity, R extends BumsoftRepository<E, Long>, D extends BumsoftResponse>
                implements BumsoftService<E> {

    private R repository;
    private AbstractObjectsMapper<E, D> mapper;

    protected AbstractBumsoftService(R repository, AbstractObjectsMapper<E, D> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * {@inheritDoc}
     */
    public ResponseEntity<BumsoftResponse> read(Long id) {
        Optional<E> response = repository.findById(id);
        if (response.isPresent()) {
            return ResponseEntity.ok(mapper.toDto(response.get()));
        }
        ErrorResponse errorResponse = ErrorResponse.builder().errorMessage("Resource not found").errorReason("Bad parameter [ID="+id+"]").build();
        return ResponseEntity.status(BumsoftResponseCode.RESOURCE_NOT_FOUND.getCode()).body(errorResponse);
    }

    /**
     * {@inheritDoc}
     */
    public ResponseEntity<BumsoftResponse> create(E entity) {
        this.beforeCreate(entity);
        E response = repository.save(entity);
        this.afterCreate(entity);
        return ResponseEntity.ok(mapper.toDto(response));
    }

    public void beforeCreate(E entity) {
        log.info("Before create");
    }

    public void afterCreate(E entity) {
        log.info("After create");
    }


    /**
     * {@inheritDoc}
     */
    public ResponseEntity<BumsoftResponse> update(Long id, E entity) {
        return null;
    }

    public void beforeUpdate(E entity) {
        log.info("Before update");
    }

    public void afterUpdate(E entity) {
        log.info("After update");
    }


    /**
     * {@inheritDoc}
     */
    public ResponseEntity<Void> delete(Long id) {
        return null;
    }

    public void beforeDelete(E entity) {
        log.info("Before delete");
    }

    public void afterDelete(E entity) {
        log.info("After delete");
    }
}
