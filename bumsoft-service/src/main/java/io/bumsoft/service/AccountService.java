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
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Slf4j
@Service
public class AccountService extends AbstractBumsoftService<Account, AccountDto, AccountMapper, Long, AccountRepository> {

    private final AccountRepository repository;
    private final ReferenceEntityTypeService referenceService;
    private final ReferenceEntityTypeMapper referenceMapper;
    private final AccountNumberGenerator anGenerator;
    private final AccountMapper mapper;

    @Autowired
    public AccountService(
            AccountRepository repository,
            AccountMapper mapper,
            ReferenceEntityTypeService referenceService,
            ReferenceEntityTypeMapper referenceMapper,
            AccountNumberGenerator anGenerator,
            ValidationService<Account> validationService) {
        super(repository, mapper, validationService);
        this.repository = repository;
        this.referenceService = referenceService;
        this.referenceMapper = referenceMapper;
        this.anGenerator = anGenerator;
        this.mapper = mapper;
    }

    /**
     * Additional process before persisting the entity
     *
     * @param entity
     * @throws BumsoftException
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
     * @param entity
     * @throws BumsoftException
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
     * @param entity
     * @throws BumsoftException
     */
    @Override
    public void processBeforeUpdate(Long id, Account entity) throws BumsoftException {
        log.info("Account creation process before update");
        this.repository.findById(id).ifPresent(account -> {
            if (Strings.isEmpty(entity.getName())) {
                entity.setName(account.getName());
            }
            if (Strings.isEmpty(entity.getDescription())) {
                entity.setDescription(account.getDescription());
            }
            if (isNull(account.getUserId())) {
                entity.setUserId(account.getUserId());
            }
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
     * @param entity
     * @throws BumsoftException
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
     * @param accountId
     * @return
     */
    public Either<BumsoftException, BumsoftResponse> findAccountBalance(final Long accountId) {
        Either<BumsoftException, AccountDto>  response = find(accountId);
        if (response.isRight()) {
            AccountDto account = response.get();
            Double accountBalance = account.getTransactions().stream().mapToDouble(TransactionDto::getValue).sum();
            return Either.right(AccountSnapshot.builder().accountBalance(accountBalance).accountInfo(account).build());
        }
        return Either.left(response.getLeft());
    }

    public Either<BumsoftException, List<AccountDto>> findAllByUserId(Long userId) {
        List<Account> accounts = this.repository.findByUserId(userId);
        if (accounts.isEmpty()) {
            log.warn("No account found");
            return Either.left(new BumsoftException("No account found"));
        }
        return Either.right(accounts.stream().map(mapper::toDto).toList());
    }
}
