package io.bumsoft.service;

import io.bumsoft.dao.entity.Account;
import io.bumsoft.dao.repository.AccountRepository;
import io.bumsoft.dto.BumsoftResponse;
import io.bumsoft.dto.acounts.AccountSnapshot;
import io.bumsoft.dto.common.AccountDto;
import io.bumsoft.dto.common.ReferenceEntityTypeDto;
import io.bumsoft.dto.common.TransactionDto;
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
public class AccountService extends AbstractBumsoftService<Account, AccountDto, AccountMapper, Long, AccountRepository> {

    private final AccountRepository repository;
    private final ReferenceEntityTypeService referenceService;
    private final ReferenceEntityTypeMapper referenceMapper;
    private final AccountNumberGenerator accountNumberGenerator;
    @Autowired
    public AccountService(
            AccountRepository repository,
            AccountMapper mapper,
            ReferenceEntityTypeService referenceService,
            ReferenceEntityTypeMapper referenceMapper,
            AccountNumberGenerator accountNumberGenerator) {
        super(repository, mapper);
        this.repository = repository;
        this.referenceService = referenceService;
        this.referenceMapper = referenceMapper;
        this.accountNumberGenerator = accountNumberGenerator;
    }

    /**
     * Additional process before persisting the entity
     *
     * @param entity
     * @throws BumsoftException
     */
    @Override
    void processBeforeCreate(Account entity) throws BumsoftException {
        log.info("Account creation process before create");
        if (nonNull(entity.getId())) {
            log.error("Account creation failed - The account id must be null for a create operation");
            throw new BumsoftException("The ID must be null for a create operation");
        }
        if (nonNull(entity.getAccountNumber())) {
            log.error("Account creation failed - The account number must be null for a create operation");
            throw new BumsoftException("The account number must be null for a create operation");
        }

        // Set account number
        entity.setAccountNumber(accountNumberGenerator.generateUniqueAccountNumber(entity.getAccountType().getName(), 7));

        // Set creation date
        entity.setCreatedAt(LocalDate.now());

        // Set account type
        Either<BumsoftException, ReferenceEntityTypeDto> ref = referenceService.findByName(entity.getAccountType().getName());
        if (ref.isLeft()) {
            throw ref.getLeft();
        }
        entity.setAccountType(referenceMapper.toEntity(ref.get()));
    }

    /**
     * Additional process after the object has been persisted
     *
     * @param entity
     * @throws BumsoftException
     */
    @Override
    void processAfterCreate(Account entity) throws BumsoftException {
        log.info("Account creation process after create");
        if (!this.repository.existsById(entity.getId())) {
            log.error("Account creation failed!");
            throw new BumsoftException("Account creation failed");
        }
        log.info("Account successfully created!");
    }

    /**
     * Additional process before update
     *
     * @param entity
     * @throws BumsoftException
     */
    @Override
    void processBeforeUpdate(Long id, Account entity) throws BumsoftException {
        log.info("Account creation process before update");
    }

    /**
     * Additional process after update
     *
     * @param entity
     * @throws BumsoftException
     */
    @Override
    void processAfterUpdate(Long id, Account entity) throws BumsoftException {
        log.info("Process after update");
    }

    public Either<BumsoftException, BumsoftResponse> findAccountBalance(final Long accountId) {
        Either<BumsoftException, AccountDto>  response = read(accountId);
        if (response.isRight()) {
            AccountDto account = response.get();
            Double accountBalance = account.getTransactions().stream().mapToDouble(TransactionDto::getValue).sum();
            AccountSnapshot accountSnapshot =
                    AccountSnapshot
                            .builder()
                                .accountBalance(accountBalance)
                                .accountInfo(account)
                            .build();
            return Either.right(accountSnapshot);
        }
        return Either.left(response.getLeft());
    }
}
