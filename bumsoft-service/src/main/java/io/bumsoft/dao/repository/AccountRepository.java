package io.bumsoft.dao.repository;

import io.bumsoft.dao.entity.Account;
import io.bumsoft.dao.entity.BumsoftUser;
import io.bumsoft.dao.entity.ReferenceEntityType;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends BumsoftRepository<Account, Long> {
    Optional<Account> findByUserAndAccountType(BumsoftUser user, ReferenceEntityType accountType);
    Optional<Account> findByAccountNumber(String accountNumber);
}
