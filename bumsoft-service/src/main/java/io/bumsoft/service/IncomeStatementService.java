package io.bumsoft.service;

import io.bumsoft.dao.entity.IncomeStatement;
import io.bumsoft.dao.entity.Transaction;
import io.bumsoft.dao.repository.IncomeStatementRepository;
import io.bumsoft.dto.BumsoftResponse;
import io.bumsoft.dto.common.ExpenseItem;
import io.bumsoft.dto.common.IncomeStatementDto;
import io.bumsoft.exception.BumsoftException;
import io.bumsoft.helper.BumsoftObjectBuilder;
import io.bumsoft.mapper.IncomeStatementMapper;
import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.ofNullable;

@Slf4j
@Service
public class IncomeStatementService extends AbstractBumsoftService<IncomeStatement, IncomeStatementDto, IncomeStatementMapper, Long, IncomeStatementRepository> {

    private final IncomeStatementRepository repository;
    private final IncomeStatementMapper mapper;

    @Autowired
    public IncomeStatementService(IncomeStatementRepository repository, IncomeStatementMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Additional process before persisting the entity
     *
     * @param entity
     * @throws BumsoftException
     */
    @Override
    void processBeforeCreate(IncomeStatement entity) throws BumsoftException {

    }

    /**
     * Additional process after the object has been persisted
     *
     * @param entity
     * @throws BumsoftException
     */
    @Override
    void processAfterCreate(IncomeStatement entity) throws BumsoftException {

    }

    /**
     * Additional process before update
     *
     * @param aLong
     * @param entity
     * @throws BumsoftException
     */
    @Override
    void processBeforeUpdate(Long aLong, IncomeStatement entity) throws BumsoftException {

    }

    /**
     * Additional process after update
     *
     * @param aLong
     * @param entity
     * @throws BumsoftException
     */
    @Override
    void processAfterUpdate(Long aLong, IncomeStatement entity) throws BumsoftException {

    }


    public Either<BumsoftException, BumsoftResponse> findByIncomeType(Long userId, LocalDate from, LocalDate until, final String incomeType) {
        List<IncomeStatement> expenses = repository.findAllByIncomeType(userId, incomeType);

        if (expenses.isEmpty())
            return Either.left(new BumsoftException("No results"));

        List<ExpenseItem> expenseItems = new ArrayList<>();

        for (IncomeStatement expense : expenses) {
            double actualValue =
                    ofNullable(expense.getTransactions())
                        .orElse(List.of()).stream()
                        .filter(trx -> trx.getProcessingDate().isAfter(from) && trx.getProcessingDate().isBefore(until))
                        .mapToDouble(Transaction::getValue).sum();
            expenseItems.add(BumsoftObjectBuilder.build(expense, Math.abs(actualValue)));
        }
        return Either.right(BumsoftObjectBuilder.build(expenseItems, from, until));
    }


}
