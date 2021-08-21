package io.bumsoft.service;

import io.bumsoft.dao.entity.Account;
import io.bumsoft.dao.repository.AccountRepository;
import io.bumsoft.helper.BumsoftUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountNumberGenerator {

    private final AccountRepository accountRepository;

    public String generateUniqueAccountNumber(String accountType, int numberOfDigits) {
        do {
            String typeCode = accountType.equals("BUDGET") ? "B" : "L";
            String accountNumber = typeCode + BumsoftUtils.randomNumber(numberOfDigits);
            Optional<Account> findAccountRes = this.accountRepository.findByAccountNumber(accountNumber);
            if (findAccountRes.isEmpty()) {
                return accountNumber;
            }
        } while (true);
    }
}
