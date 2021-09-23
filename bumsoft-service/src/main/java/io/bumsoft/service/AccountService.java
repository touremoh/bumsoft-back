package io.bumsoft.service;

import io.bumsoft.dao.entity.Account;
import io.bumsoft.dao.repository.AccountRepository;
import io.bumsoft.dao.specifications.AccountQueryBuilder;
import io.bumsoft.dto.BumsoftResponse;
import io.bumsoft.dto.acounts.AccountSnapshot;
import io.bumsoft.dto.common.AccountDto;
import io.bumsoft.dto.common.ReferenceEntityTypeDto;
import io.bumsoft.dto.common.TransactionDto;
import io.bumsoft.dto.response.ErrorResponse;
import io.bumsoft.exception.BumsoftException;
import io.bumsoft.mapper.AccountMapper;
import io.bumsoft.mapper.ReferenceEntityTypeMapper;
import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static java.util.Objects.nonNull;

@Slf4j
@Service
public class AccountService extends AbstractBumsoftService<Account, AccountDto, Long> {

    private final AccountRepository repository;
    private final ReferenceEntityTypeService referenceService;
    private final ReferenceEntityTypeMapper referenceMapper;
    private final AccountNumberGenerator anGenerator;

    @Autowired
    public AccountService(
            AccountRepository repository,
            AccountMapper mapper,
            ReferenceEntityTypeService referenceService,
            ReferenceEntityTypeMapper referenceMapper,
            AccountNumberGenerator anGenerator,
            ValidationService<Account> validationService,
            AccountQueryBuilder queryBuilder) {
        super(repository, mapper, validationService, queryBuilder);
        this.repository = repository;
        this.referenceService = referenceService;
        this.referenceMapper = referenceMapper;
        this.anGenerator = anGenerator;
    }

    /**
     * Additional process before persisting the entity
     *
     * @param entity resource to process before create
     * @throws BumsoftException exception thrown when an error occurs
     */
    @Override
    public void processBeforeCreate(Account entity) throws BumsoftException {
        log.info("Account creation process before create");
        if (nonNull(entity.getAccountNumber())) {
            log.error("Account creation failed - Unauthorized value found - ACCOUNT NUMBER: "+entity.getAccountNumber());
            throw new BumsoftException("[accountNumber:The account number is not authorized]");
        }
        // Set account type
        Either<BumsoftException, ReferenceEntityTypeDto> res = referenceService.findByName(entity.getAccountType().getName());
        if (res.isLeft()) {
            throw new BumsoftException(res.getLeft());
        }
        entity.setAccountNumber(anGenerator.generateUniqueAccountNumber(entity.getAccountType().getName(), 7));
        entity.setAccountType(referenceMapper.toEntity(res.get()));
        entity.setCreatedAt(LocalDate.now());
    }

    /**
     * Additional process after the object has been persisted
     *
     * @param entity resource to process after create
     * @throws BumsoftException exception thrown when an error occurs
     */
    @Override
    public void processAfterCreate(Account entity) throws BumsoftException {
        log.info("Account creation process after create");
        if (!this.repository.existsById(entity.getId())) {
            log.error("Account creation failed!");
            throw new BumsoftException("Account creation failed");
        }
        log.info("Account successfully created!");
    }

    /**
     * Additional process before update
     * @param entity resource to process before update
     */
    @Override
    public void processBeforeUpdate(Long id, Account entity) {
        log.info("Account creation process before update");
        this.repository.findById(id).ifPresent(account -> {
            patch(entity, account, Account::getName, (Account a, Object v) -> a.setName((String) v));
            patch(entity, account, Account::getDescription, (Account a, Object v) -> a.setDescription((String) v));
            patch(entity, account, Account::getUserId, (Account a, Object v) -> a.setUserId((Long) v));
            entity.setId(account.getId());
            entity.setAccountNumber(account.getAccountNumber());
            entity.setAccountType(account.getAccountType());
            entity.setCreatedAt(account.getCreatedAt());
            entity.setTransactions(account.getTransactions());
            entity.setUpdatedAt(LocalDate.now());
        });
    }

    /**
     * Additional process after update
     *
     * @param entity resource to process before update
     * @throws BumsoftException exception thrown when an error occurs
     */
    @Override
    public void processAfterUpdate(Long id, Account entity) throws BumsoftException {
        log.info("Account update process after save operation");
        if (!id.equals(entity.getId())) {
            log.error("Account update failed!");
            throw new BumsoftException("Account update failed!");
        }
        log.info("Account successfully updated!");
    }

    /**
     * Find account balance
     * @param accountId id of the account
     * @return the account balance
     */
    public Either<ErrorResponse, BumsoftResponse> findAccountBalance(final Long accountId) {
        return this.find(accountId).bimap(error -> error, account -> {
            Double accountBalance = account.getTransactions().stream().mapToDouble(TransactionDto::getValue).sum();
            return AccountSnapshot.builder().accountBalance(accountBalance).accountInfo(account).build();
        });
    }
}
