package io.bumsoft.dto.common;

import io.bumsoft.dto.BumsoftResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class ExpenditureSummary implements BumsoftResponse {
    private LocalDate from;
    private LocalDate until;
    private List<ExpenseItem> expenseItems;
    private Double totalExpectedValue;
    private Double totalActualValue;
    private Double totalRemainingValue;
}
