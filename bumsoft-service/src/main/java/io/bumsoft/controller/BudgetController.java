package io.bumsoft.controller;

import io.bumsoft.dto.common.BudgetDto;
import io.bumsoft.helper.ApiResponse;
import io.bumsoft.service.BudgetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/budgets")
public class BudgetController  extends AbstractBumsoftController<BudgetDto, Long, BudgetService> {

    private final BudgetService service;

    protected BudgetController(BudgetService service) {
        super(service);
        this.service = service;
    }

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findByUserId(@RequestParam Long userId) {
        return ApiResponse.ofRead(service.findByUserId(userId));
    }
}
