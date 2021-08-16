package io.bumsoft.rest;

import io.bumsoft.dto.BumsoftResponse;
import io.bumsoft.dto.common.IncomeStatementDto;
import io.bumsoft.exception.BumsoftException;
import io.bumsoft.service.IncomeStatementService;
import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@Slf4j
@RestController
@RequestMapping(path = "/statements")
public class IncomeStatementController extends AbstractBumsoftController<IncomeStatementDto, Long, IncomeStatementService> {

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

        Either<BumsoftException, BumsoftResponse> responses = this.incomeStatementService.findByIncomeType(userId, from, until, incomeType);
        return responses.isRight() ?
                ResponseEntity.accepted().body(responses.get()) :
                ResponseEntity.noContent().build();
    }
}
