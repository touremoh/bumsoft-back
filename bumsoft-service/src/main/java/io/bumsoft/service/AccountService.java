package io.bumsoft.service;

import io.bumsoft.constants.BumsoftResponseCode;
import io.bumsoft.dao.entity.Account;
import io.bumsoft.dao.entity.Transaction;
import io.bumsoft.dao.repository.AccountRepository;
import io.bumsoft.dto.BumsoftResponse;
import io.bumsoft.dto.acounts.AccountSnapshot;
import io.bumsoft.dto.common.AccountDto;
import io.bumsoft.dto.common.ErrorResponse;
import io.bumsoft.mapper.AbstractObjectsMapper;
import io.bumsoft.mapper.AccountMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class AccountService extends AbstractBumsoftService<Account, AccountRepository, AccountDto> {

    private final AccountRepository repository;
    private final AbstractObjectsMapper<Account, AccountDto> mapper;

    @Autowired
    public AccountService(AccountRepository repository, AccountMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
    }

    public ResponseEntity<BumsoftResponse> findAccountBalance(final Long accountId) {
        Optional<Account>  response = this.repository.findById(accountId);
        if (response.isPresent()) {
            Account account = response.get();
            Double accountBalance = account.getTransactions().stream().mapToDouble(Transaction::getValue).sum();
            AccountDto accountInfo = mapper.toDto(account);
            AccountSnapshot accountSnapshot =
                    AccountSnapshot
                            .builder()
                                .accountBalance(accountBalance)
                                .accountInfo(accountInfo)
                            .build();
            return ResponseEntity.ok(accountSnapshot);
        }
        ErrorResponse errorResponse =
                ErrorResponse
                    .builder()
                        .errorMessage(BumsoftResponseCode.RESOURCE_NOT_FOUND.getDescription())
                        .errorReason("Invalid parameter")
                    .build();
        return ResponseEntity.status(BumsoftResponseCode.RESOURCE_NOT_FOUND.getCode()).body(errorResponse);
    }

    /**
     * This method is used to process the object before persisting it
     *
     * @param object to be process before persistence
     * @return the entity to be persisted
     */
    @Override
    public Account processBeforeCreate(AccountDto object) {
        log.info("Processing the account");
        return null;
    }

    /**
     * Process after the object is persisted
     *
     * @param entity persisted object
     * @return void
     */
    @Override
    public AccountDto processAfterCreate(Account entity) {
        log.info("After creating the account [" + entity.getId() + "]");
        return null;
    }
}
