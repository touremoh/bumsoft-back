package io.bumsoft.dao.repository;

import io.bumsoft.dao.entity.IncomeStatement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeStatementRepository extends JpaRepository<IncomeStatement, Long> {

    @Query("SELECT inc_smt FROM IncomeStatement inc_smt " +
           "WHERE inc_smt.beneficiaryUser.id = :userId " +
           "AND inc_smt.incomeType.group = 'INCOME_STATEMENT_TYPE' " +
           "AND inc_smt.incomeType.name = :name")
    List<IncomeStatement> findAllByIncomeType(@Param("userId") Long userId, @Param("name") String incomeType);
}
