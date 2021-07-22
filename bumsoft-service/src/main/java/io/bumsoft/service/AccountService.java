package io.bumsoft.service;

import io.bumsoft.dao.entity.Account;
import io.bumsoft.dao.entity.BumsoftUser;
import io.bumsoft.dao.entity.ReferenceEntityType;
import io.bumsoft.dao.repository.AccountRepository;
import io.bumsoft.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AccountService extends AbstractBumsoftService<Account, AccountRepository> {

    private final AccountRepository repository;
    private final BumsoftUserService userService;

    @Autowired
    public AccountService(AccountRepository repository, BumsoftUserService userService) {
        super(repository);
        this.repository = repository;
        this.userService = userService;
    }
}
