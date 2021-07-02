package io.bumsoft.dto.planifications;

import io.bumsoft.dto.BumsoftResponse;
import io.bumsoft.dto.common.IncomeStatementDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class IncomeSource implements BumsoftResponse {
    private LocalDate from;
    private LocalDate until;
    private List<IncomeStatementDto> incomeStatement;
    private Double totalIncome;
}
