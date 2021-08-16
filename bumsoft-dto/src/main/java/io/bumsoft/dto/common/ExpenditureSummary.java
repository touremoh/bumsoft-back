package io.bumsoft.dto.common;

import io.bumsoft.dto.BumsoftDto;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpenditureSummary implements BumsoftDto {
    private LocalDate from;
    private LocalDate until;
    private List<ExpenseItem> expenseItems;
    private Double totalExpectedAmount;
    private Double totalActualAmount;
    private Double totalDelta;
}
