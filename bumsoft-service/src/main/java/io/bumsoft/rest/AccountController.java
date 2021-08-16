package io.bumsoft.rest;

import io.bumsoft.dto.BumsoftResponse;
import io.bumsoft.dto.common.AccountDto;
import io.bumsoft.exception.BumsoftException;
import io.bumsoft.service.AccountService;
import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        Either<BumsoftException, BumsoftResponse> response = accountService.findAccountBalance(accountId);
        return response.isRight() ?
                ResponseEntity.accepted().body(response.get()) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(response.getLeft().getMessage());
    }
}
