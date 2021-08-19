package io.bumsoft.service;

import io.bumsoft.dao.entity.Budget;
import io.bumsoft.dao.repository.BudgetRepository;
import io.bumsoft.dto.common.BudgetDto;
import io.bumsoft.exception.BumsoftException;
import io.bumsoft.mapper.BudgetMapper;
import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BudgetService extends AbstractBumsoftService<Budget, BudgetDto, BudgetMapper, Long, BudgetRepository> {

    private final BudgetRepository repository;
    private final BudgetMapper mapper;

    @Autowired
    protected BudgetService(BudgetRepository repository, BudgetMapper mapper) {
        super(repository, mapper);
        this.mapper = mapper;
        this.repository = repository;
    }

    public Either<BumsoftException, List<BudgetDto>> findByUserId(Long userId) {
        List<BudgetDto> userBudgets = this.repository.findByUserId(userId).stream().map(mapper::toDto).collect(Collectors.toList());
        return userBudgets.isEmpty() ? Either.left(new BumsoftException("No budget found for user " + userId)) : Either.right(userBudgets);
    }

    /**
     * Additional process before persisting the entity
     *
     * @param entity
     * @throws BumsoftException
     */
    @Override
    void processBeforeCreate(Budget entity) throws BumsoftException {

    }

    /**
     * Additional process after the object has been persisted
     *
     * @param entity
     * @throws BumsoftException
     */
    @Override
    void processAfterCreate(Budget entity) throws BumsoftException {

    }

    /**
     * Additional process before update
     *
     * @param id
     * @param entity
     * @throws BumsoftException
     */
    @Override
    void processBeforeUpdate(Long id, Budget entity) throws BumsoftException {

    }

    /**
     * Additional process after update
     *
     * @param id
     * @param entity
     * @throws BumsoftException
     */
    @Override
    void processAfterUpdate(Long id, Budget entity) throws BumsoftException {

    }
}
