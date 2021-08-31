package io.bumsoft.dao.repository;

import io.bumsoft.dao.entity.Account;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends BumsoftRepository<Account, Long> {
    /**
     * Find account by acount number
     * @param accountNumber
     * @return
     */
    Optional<Account> findByAccountNumber(String accountNumber);

    /**
     * Find all accounts by users
     * @param userId
     * @return
     */
    List<Account> findByUserId(Long userId);
}
