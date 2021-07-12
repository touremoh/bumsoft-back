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
                    .expectedValue(expense.getExpectedAmount())
                    .actualValue(actualValue)
                    .remainingValue(expense.getExpectedAmount() - actualValue)
                .build();
    }
    public static ExpenditureSummary build(List<ExpenseItem> expenseItems, LocalDate from, LocalDate until) {
        Double expectedValue = expenseItems.stream().mapToDouble(ExpenseItem::getExpectedValue).sum();
        Double actualValue = expenseItems.stream().mapToDouble(ExpenseItem::getActualValue).sum();
        return ExpenditureSummary
                .builder()
                    .from(from)
                    .until(until)
                    .expenseItems(expenseItems)
                    .totalExpectedValue(expectedValue)
                    .totalActualValue(actualValue)
                    .totalRemainingValue(expectedValue - actualValue)
                .build();
    }
}
