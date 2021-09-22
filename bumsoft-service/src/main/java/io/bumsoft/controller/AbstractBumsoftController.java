package io.bumsoft.controller;

import io.bumsoft.dto.BumsoftDto;
import io.bumsoft.helper.ApiResponse;
import io.bumsoft.service.BumsoftService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
public abstract class AbstractBumsoftController<D extends BumsoftDto, ID, S extends BumsoftService<D, ID>> {

    private final S service;

    protected AbstractBumsoftController(S service) {
        this.service = service;
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findById(@PathVariable ID id) {
        return ApiResponse.ofRead(service.find(id));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody D bumsoftDto)  {
        return ApiResponse.ofCreate(service.create(bumsoftDto));
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(@PathVariable ID id, @RequestBody D bumsoftDto) {
        return ApiResponse.ofUpdate(service.update(id, bumsoftDto));
    }

    @DeleteMapping(path="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@PathVariable ID id) {
        return ApiResponse.ofDelete(this.service.delete(id));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findByCriteria(@RequestParam Map<String, String> criteria) {
        return ApiResponse.ofRead(service.findAllByCriteria(criteria));
    }

}
