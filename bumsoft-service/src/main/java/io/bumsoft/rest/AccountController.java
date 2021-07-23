package io.bumsoft.rest;

import io.bumsoft.dao.entity.Account;
import io.bumsoft.dao.repository.AccountRepository;
import io.bumsoft.dto.common.AccountDto;
import io.bumsoft.mapper.AccountMapper;
import io.bumsoft.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/accounts")
public class AccountController extends AbstractBumsoftController<Account, AccountRepository, AccountService, AccountDto> {
    private final AccountService accountService;
    private final AccountMapper mapper;

    public AccountController(AccountService accountService, AccountMapper mapper) {
        super(accountService, mapper);
        this.accountService = accountService;
        this.mapper = mapper;
    }
}
