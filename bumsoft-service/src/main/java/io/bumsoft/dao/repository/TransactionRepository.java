package io.bumsoft.dao.repository;

import io.bumsoft.dao.entity.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends BumsoftRepository<Transaction, Long> {
}
