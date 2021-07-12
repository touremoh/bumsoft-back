package io.bumsoft.rest;

import io.bumsoft.dto.BumsoftResponse;
import io.bumsoft.service.IncomeStatementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Slf4j
@RestController
@RequestMapping("/is")
public class IncomeStatementController {

    private final IncomeStatementService incomeStatementService;

    @Autowired
    public IncomeStatementController(IncomeStatementService incomeStatementService) {
        this.incomeStatementService = incomeStatementService;
    }

    @GetMapping(path = "/summary")
    public ResponseEntity<BumsoftResponse> expenditureSummary(
            @RequestParam("userId") Long userId,
            @RequestParam("from") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate from,
            @RequestParam("until") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate until) {
        log.info("[IncomeStatement] - Expenditure summary");
        return ResponseEntity.ok(this.incomeStatementService.getExpenditureSummary(userId, from, until));
    }
}
