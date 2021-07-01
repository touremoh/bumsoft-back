package io.bumsoft.dao.repository;

import io.bumsoft.dao.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountRepository extends JpaRepository<Account, Long> {

}
