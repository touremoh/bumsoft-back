package io.bumsoft.dto.common;

import io.bumsoft.dto.BumsoftResponse;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpenditureSummary implements BumsoftResponse {
    private LocalDate from;
    private LocalDate until;
    private List<ExpenseItem> expenseItems;
    private Double totalExpectedAmount;
    private Double totalActualAmount;
    private Double totalDelta;
}
