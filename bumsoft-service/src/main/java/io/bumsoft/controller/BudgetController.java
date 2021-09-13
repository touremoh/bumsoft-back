package io.bumsoft.controller;

import io.bumsoft.dto.common.BudgetDto;
import io.bumsoft.service.BudgetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/budgets")
public class BudgetController  extends AbstractBumsoftController<BudgetDto, Long, BudgetService> {

    private final BudgetService service;

    protected BudgetController(BudgetService service) {
        super(service);
        this.service = service;
    }
}
