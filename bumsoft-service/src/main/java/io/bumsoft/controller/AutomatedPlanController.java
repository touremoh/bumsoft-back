package io.bumsoft.controller;

import io.bumsoft.dto.common.AutomatedPlanDto;
import io.bumsoft.helper.ApiResponse;
import io.bumsoft.service.AutomatedPlanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/plans")
public class AutomatedPlanController extends AbstractBumsoftController<AutomatedPlanDto, Long, AutomatedPlanService> {

    private final AutomatedPlanService service;

    protected AutomatedPlanController(AutomatedPlanService service) {
        super(service);
        this.service = service;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findAllByUserId(@RequestParam Long userId) {
        return ApiResponse.ofRead(service.findByUserId(userId));
    }
}
