package io.bumsoft.rest;

import io.bumsoft.constants.BumsoftResponseCode;
import io.bumsoft.dao.entity.BumsoftEntity;
import io.bumsoft.dao.repository.BumsoftRepository;
import io.bumsoft.dto.BumsoftResponse;
import io.bumsoft.dto.common.ErrorResponse;
import io.bumsoft.exception.ResourceNotFoundException;
import io.bumsoft.mapper.AbstractObjectsMapper;
import io.bumsoft.service.AbstractBumsoftService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
public abstract class AbstractBumsoftController<E extends BumsoftEntity, R extends BumsoftRepository<E, Long>, S extends AbstractBumsoftService<E, R>, D extends BumsoftResponse> {

    private final S myService;
    private final AbstractObjectsMapper<E, D> mapper;

    protected AbstractBumsoftController(S myService, AbstractObjectsMapper<E, D> mapper) {
        this.myService = myService;
        this.mapper = mapper;
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BumsoftResponse> findById(@PathVariable long id) {
        try {
            return ResponseEntity.ok(mapper.toDto(myService.findById(id)));
        } catch (ResourceNotFoundException ex) {
            log.error("Resource not found with ID ["+id+"]");
        }
        ErrorResponse response = ErrorResponse.builder().errorMessage("Resource not found").errorReason("Bad parameter [ID="+id+"]").build();
        return ResponseEntity.status(BumsoftResponseCode.RESOURCE_NOT_FOUND.getCode()).body(response);
    }
}
