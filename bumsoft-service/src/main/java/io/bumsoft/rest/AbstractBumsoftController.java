package io.bumsoft.rest;

import io.bumsoft.dao.entity.BumsoftEntity;
import io.bumsoft.dao.repository.BumsoftRepository;
import io.bumsoft.dto.BumsoftResponse;
import io.bumsoft.mapper.AbstractObjectsMapper;
import io.bumsoft.service.AbstractBumsoftService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
public abstract class AbstractBumsoftController<E extends BumsoftEntity, R extends BumsoftRepository<E, Long>, S extends AbstractBumsoftService<E, R, D>, D extends BumsoftResponse> {

    private final S myService;
    private final AbstractObjectsMapper<E, D> mapper;

    protected AbstractBumsoftController(S myService, AbstractObjectsMapper<E, D> mapper) {
        this.myService = myService;
        this.mapper = mapper;
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BumsoftResponse> findById(@PathVariable long id) {
        return myService.findById(id);
    }
}
