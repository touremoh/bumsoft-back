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

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static org.mapstruct.ap.internal.util.Strings.isEmpty;

@Slf4j
@Service
public class BudgetService extends AbstractBumsoftService<Budget, BudgetDto, BudgetMapper, Long, BudgetRepository> {

    private final BudgetRepository repository;
    private final BudgetMapper mapper;

    @Autowired
    protected BudgetService(BudgetRepository repository, BudgetMapper mapper, ValidationService<Budget> validationService) {
        super(repository, mapper, validationService);
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
    public void processBeforeCreate(Budget entity) throws BumsoftException {
        log.info("Budget Creation Before Update");
        // Check if the user exists
        // Check if account exists
        entity.setCreatedAt(LocalDate.now());
    }

    /**
     * Additional process after the object has been persisted
     *
     * @param entity
     * @throws BumsoftException
     */
    @Override
    public void processAfterCreate(Budget entity) throws BumsoftException {
        log.info("Checking if persistence was ok");
        if (!this.repository.existsById(entity.getId())) {
            log.error("Unable to persist object with ID: " + entity.getId());
            throw new BumsoftException("The budget creation failed");
        }
        log.info("Budget object successfully persisted");
    }

    /**
     * Additional process before update
     *
     * @param id
     * @param entity
     * @throws BumsoftException
     */
    @Override
    public void processBeforeUpdate(Long id, Budget entity) throws BumsoftException {
        this.repository.findById(id).ifPresent(budget -> {
            if (isEmpty(entity.getName())) {
                entity.setName(budget.getName());
            }
            if (isNull(entity.getAmount())) {
                entity.setAmount(budget.getAmount());
            }
            if (isNull(entity.getUserId())) {
                entity.setUserId(budget.getUserId());
            }
            if (isNull(entity.getAccountId())) {
                entity.setAccountId(budget.getAccountId());
            }
            entity.setId(budget.getId());
            entity.setCreatedAt(budget.getCreatedAt());
            entity.setUpdatedAt(LocalDate.now());
        });
        checkIfInputIsValid(entity);
    }

    /**
     * Additional process after update
     *
     * @param id
     * @param entity
     * @throws BumsoftException
     */
    @Override
    public void processAfterUpdate(Long id, Budget entity) throws BumsoftException {
        log.info("Process after update");
        if (!id.equals(entity.getId())) {
            log.error("Update budget failed");
            throw new BumsoftException("The budget with ID [" + id + "] was not updated ");
        }
    }
}
