package io.bumsoft.dao.repository;

import io.bumsoft.dao.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT trx FROM Transaction trx " +
           "INNER JOIN IncomeStatement inc " +
           "ON inc.id = trx.incomeStatement.id " +
           "AND inc.beneficiaryUser.id = :userId " +
           "AND inc.incomeType.id in " +
           "    (SELECT ref.id" +
           "     FROM ReferenceEntityType ref" +
           "     WHERE ref.group = :incStmtGroup AND ref.name = :incStmtName) " +
           "WHERE trx.relatedAccount.id =  :accountId " +
           "AND trx.processingDate BETWEEN :from AND :until")
    List<Transaction> findAllByIncomeType(
            @Param("userId") Long userId,
            @Param("accountId") Long accountId,
            @Param("incStmtGroup") String incomeStatementGroup,
            @Param("incStmtName") String incomeStatementName,
            @Param("from") LocalDate from,
            @Param("until") LocalDate until
    );
}
