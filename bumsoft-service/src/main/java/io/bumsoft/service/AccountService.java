package io.bumsoft.service;

import io.bumsoft.dao.entity.Account;
import io.bumsoft.dao.repository.AccountRepository;
import io.bumsoft.dto.BumsoftResponse;
import io.bumsoft.dto.acounts.AccountSnapshot;
import io.bumsoft.dto.common.AccountDto;
import io.bumsoft.dto.common.TransactionDto;
import io.bumsoft.exception.BumsoftException;
import io.bumsoft.mapper.AccountMapper;
import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AccountService extends AbstractBumsoftService<Account, AccountDto, AccountMapper, Long, AccountRepository> {

    private final AccountRepository repository;
    private final AccountMapper mapper;

    @Autowired
    public AccountService(AccountRepository repository, AccountMapper mapper) {
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
    void processBeforeCreate(Account entity) throws BumsoftException {
        log.info("Process  before create");
    }

    /**
     * Additional process after the object has been persisted
     *
     * @param entity
     * @throws BumsoftException
     */
    @Override
    void processAfterCreate(Account entity) throws BumsoftException {
        log.info("Process after create");
    }

    /**
     * Additional process before update
     *
     * @param entity
     * @throws BumsoftException
     */
    @Override
    void processBeforeUpdate(Long id, Account entity) throws BumsoftException {
        log.info("Process before update");
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
