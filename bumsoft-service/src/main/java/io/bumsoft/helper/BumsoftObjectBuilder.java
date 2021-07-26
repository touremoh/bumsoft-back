package io.bumsoft.helper;

import io.bumsoft.dao.entity.IncomeStatement;
import io.bumsoft.dto.common.ExpenditureSummary;
import io.bumsoft.dto.common.ExpenseItem;

import java.time.LocalDate;
import java.util.List;


public class BumsoftObjectBuilder {
    public static ExpenseItem build(IncomeStatement expense, Double actualValue) {
        return ExpenseItem
                .builder()
                    .label(expense.getName())
                    .expectedAmount(expense.getExpectedAmount())
                    .actualAmount(actualValue)
                    .delta(expense.getExpectedAmount() - actualValue)
                .build();
    }
    public static ExpenditureSummary build(List<ExpenseItem> expenseItems, LocalDate from, LocalDate until) {
        Double expectedValue = expenseItems.stream().mapToDouble(ExpenseItem::getExpectedAmount).sum();
        Double actualValue = expenseItems.stream().mapToDouble(ExpenseItem::getActualAmount).sum();
        return ExpenditureSummary
                .builder()
                    .from(from)
                    .until(until)
                    .expenseItems(expenseItems)
                    .totalExpectedAmount(expectedValue)
                    .totalActualAmount(actualValue)
                    .totalDelta(expectedValue - actualValue)
                .build();
    }
}
