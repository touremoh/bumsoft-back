package io.bumsoft.controller;

import io.bumsoft.dto.common.AccountDto;
import io.bumsoft.helper.ApiResponse;
import io.bumsoft.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/accounts")
public class AccountController extends AbstractBumsoftController<AccountDto, Long, AccountService> {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        super(accountService);
        this.accountService = accountService;
    }

    @GetMapping(path = "/{accountId}/balance", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findAccountBalance(@PathVariable Long accountId) {
        return ApiResponse.ofRead(accountService.findAccountBalance(accountId));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findAllByUserId(@RequestParam Long userId) {
        return ApiResponse.ofRead(accountService.findAllByUserId(userId));
    }
}
