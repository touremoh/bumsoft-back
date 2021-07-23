package io.bumsoft.service;

import io.bumsoft.constants.BumsoftResponseCode;
import io.bumsoft.dao.entity.Account;
import io.bumsoft.dao.entity.Transaction;
import io.bumsoft.dao.repository.AccountRepository;
import io.bumsoft.dto.BumsoftResponse;
import io.bumsoft.dto.acounts.AccountSnapshot;
import io.bumsoft.dto.common.AccountDto;
import io.bumsoft.dto.common.ErrorResponse;
import io.bumsoft.exception.ResourceNotFoundException;
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

    public ResponseEntity<BumsoftResponse> getAccountSnapshot(final Long accountId) {
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
}
