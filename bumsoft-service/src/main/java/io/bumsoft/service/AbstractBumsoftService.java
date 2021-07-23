package io.bumsoft.service;

import io.bumsoft.constants.BumsoftResponseCode;
import io.bumsoft.dao.entity.BumsoftEntity;
import io.bumsoft.dao.repository.BumsoftRepository;
import io.bumsoft.dto.BumsoftResponse;
import io.bumsoft.dto.common.ErrorResponse;
import io.bumsoft.mapper.AbstractObjectsMapper;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@NoArgsConstructor
public abstract class AbstractBumsoftService<E extends BumsoftEntity, R extends BumsoftRepository<E, Long>, D extends BumsoftResponse> implements BumsoftService {

    private R repository;
    private AbstractObjectsMapper<E, D> mapper;

    protected AbstractBumsoftService(R repository, AbstractObjectsMapper<E, D> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public ResponseEntity<BumsoftResponse> findById(@PathVariable Long id) {
        Optional<E> response = repository.findById(id);
        if (response.isPresent()) {
            return ResponseEntity.ok(mapper.toDto(response.get()));
        }
        ErrorResponse errorResponse = ErrorResponse.builder().errorMessage("Resource not found").errorReason("Bad parameter [ID="+id+"]").build();
        return ResponseEntity.status(BumsoftResponseCode.RESOURCE_NOT_FOUND.getCode()).body(errorResponse);
    }
}
