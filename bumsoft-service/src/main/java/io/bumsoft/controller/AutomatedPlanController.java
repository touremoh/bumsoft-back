package io.bumsoft.controller;

import io.bumsoft.dto.common.AutomatedPlanDto;
import io.bumsoft.service.AutomatedPlanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/plans")
public class AutomatedPlanController extends AbstractBumsoftController<AutomatedPlanDto, Long, AutomatedPlanService> {

    private final AutomatedPlanService service;

    protected AutomatedPlanController(AutomatedPlanService service) {
        super(service);
        this.service = service;
    }
}
