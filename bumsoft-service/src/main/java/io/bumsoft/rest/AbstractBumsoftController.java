package io.bumsoft.rest;

import io.bumsoft.dto.BumsoftDto;
import io.bumsoft.helper.ApiResponse;
import io.bumsoft.service.BumsoftService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
public abstract class AbstractBumsoftController<D extends BumsoftDto, ID, S extends BumsoftService<D, ID>> {

    private final S service;

    protected AbstractBumsoftController(S service) {
        this.service = service;
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findById(@PathVariable ID id) {
        return ApiResponse.ofRead(service.read(id));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody D myDto)  {
        return ApiResponse.ofCreate(service.create(myDto));
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(@RequestParam ID id, @RequestBody D myDto) {
        return ApiResponse.ofUpdate(service.update(id, myDto));
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity delete(@PathVariable ID id) {
        this.service.delete(id);
        return ApiResponse.ofDelete();
    }
}
