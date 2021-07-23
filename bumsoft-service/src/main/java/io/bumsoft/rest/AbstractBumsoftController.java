package io.bumsoft.rest;

import io.bumsoft.dto.BumsoftResponse;
import io.bumsoft.service.BumsoftService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
public abstract class AbstractBumsoftController<S extends BumsoftService> {

    private final S myService;

    protected AbstractBumsoftController(S myService) {
        this.myService = myService;
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BumsoftResponse> findById(@PathVariable long id) {
        return myService.findById(id);
    }
}
