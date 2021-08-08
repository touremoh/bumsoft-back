package io.bumsoft.service;

import io.bumsoft.dao.entity.IncomeStatement;
import io.bumsoft.dao.entity.Transaction;
import io.bumsoft.dao.repository.IncomeStatementRepository;
import io.bumsoft.dto.common.ExpenditureSummary;
import io.bumsoft.dto.common.ExpenseItem;
import io.bumsoft.dto.common.IncomeStatementDto;
import io.bumsoft.helper.BumsoftObjectBuilder;
import io.bumsoft.mapper.AbstractObjectsMapper;
import io.bumsoft.mapper.IncomeStatementMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.ofNullable;

@Slf4j
@Service
public class IncomeStatementService extends AbstractBumsoftService<IncomeStatement, IncomeStatementRepository, IncomeStatementDto> {

    private final IncomeStatementRepository repository;
    private final AbstractObjectsMapper<IncomeStatement, IncomeStatementDto> mapper;

    @Autowired
    public IncomeStatementService(IncomeStatementRepository repository, IncomeStatementMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
    }

    public ExpenditureSummary findByIncomeType(Long userId, LocalDate from, LocalDate until, final String incomeType) {
        List<IncomeStatement> expenses = repository.findAllByIncomeType(userId, incomeType);
        List<ExpenseItem> expenseItems = new ArrayList<>();

        for (IncomeStatement expense : expenses) {
            double actualValue =
                    ofNullable(expense.getTransactions())
                        .orElse(List.of()).stream()
                        .filter(trx -> trx.getProcessingDate().isAfter(from) && trx.getProcessingDate().isBefore(until))
                        .mapToDouble(Transaction::getValue).sum();
            expenseItems.add(BumsoftObjectBuilder.build(expense, Math.abs(actualValue)));
        }
        return BumsoftObjectBuilder.build(expenseItems, from, until);
    }

    /**
     * This method is used to process the object before persisting it
     *
     * @param object to be process before persistence
     * @return the entity to be persisted
     */
    @Override
    public IncomeStatement processBeforeCreate(IncomeStatementDto object) {
        return null;
    }

    /**
     * Process after the object is persisted
     *
     * @param entity persisted object
     * @return void
     */
    @Override
    public IncomeStatementDto processAfterCreate(IncomeStatement entity) {
        return null;
    }
}
