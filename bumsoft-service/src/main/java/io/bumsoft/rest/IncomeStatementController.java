package io.bumsoft.rest;

import io.bumsoft.dao.entity.IncomeStatement;
import io.bumsoft.dao.repository.IncomeStatementRepository;
import io.bumsoft.dto.BumsoftResponse;
import io.bumsoft.dto.common.IncomeStatementDto;
import io.bumsoft.service.AbstractBumsoftService;
import io.bumsoft.service.IncomeStatementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Slf4j
@RestController
@RequestMapping(path = "/income-statements")
public class IncomeStatementController extends AbstractBumsoftController<IncomeStatement, IncomeStatementDto, IncomeStatementRepository, IncomeStatementService> {

    private final IncomeStatementService incomeStatementService;

    @Autowired
    public IncomeStatementController(IncomeStatementService incomeStatementService) {
        super(incomeStatementService);
        this.incomeStatementService = incomeStatementService;
    }

    @GetMapping(path = "/summary", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BumsoftResponse> findByIncomeType(
            @RequestParam("userId") Long userId,
            @RequestParam("incomeType") String incomeType,
            @RequestParam("from") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate from,
            @RequestParam("until") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate until) {
        log.info("[IncomeStatement] - Expenditure summary");
        return ResponseEntity.ok(this.incomeStatementService.findByIncomeType(userId, from, until, incomeType));
    }
}
