package io.bumsoft.rest;

import io.bumsoft.dao.entity.Account;
import io.bumsoft.dao.repository.AccountRepository;
import io.bumsoft.dto.BumsoftResponse;
import io.bumsoft.dto.common.AccountDto;
import io.bumsoft.service.AbstractBumsoftService;
import io.bumsoft.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/accounts")
public class AccountController extends AbstractBumsoftController<AbstractBumsoftService<Account, AccountRepository, AccountDto>> {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        super(accountService);
        this.accountService = accountService;
    }

    @GetMapping(path = "/{accountId}/snapshot", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BumsoftResponse> findAccountBalance(@PathVariable Long accountId) {
        return accountService.getAccountSnapshot(accountId);
    }
}