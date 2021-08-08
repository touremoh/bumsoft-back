package io.bumsoft.rest;

import io.bumsoft.dao.entity.BumsoftEntity;
import io.bumsoft.dao.repository.BumsoftRepository;
import io.bumsoft.dto.BumsoftDto;
import io.bumsoft.dto.BumsoftResponse;
import io.bumsoft.dto.common.ErrorResponse;
import io.bumsoft.dto.common.TransactionDto;
import io.bumsoft.exception.ResourceNotFoundException;
import io.bumsoft.service.AbstractBumsoftService;
import io.bumsoft.service.BumsoftService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
public abstract class AbstractBumsoftController<E extends BumsoftEntity, D extends BumsoftDto, R extends BumsoftRepository<E, Long>, S extends AbstractBumsoftService<E, R, D>> {

    private final BumsoftService myService;

    protected AbstractBumsoftController(S myService) {
        this.myService = myService;
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BumsoftResponse> findById(@PathVariable long id) {
        try {
            return ResponseEntity.ok(myService.read(id));
        } catch (ResourceNotFoundException e) {
            ErrorResponse errorResponse =
                    ErrorResponse
                            .builder()
                            .errorMessage(e.getMessage())
                            .errorReason(HttpStatus.BAD_REQUEST.getReasonPhrase())
                            .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BumsoftResponse> create(@RequestBody D myDto)  {
        return ResponseEntity.ok(myService.create(myDto));
    }
}
