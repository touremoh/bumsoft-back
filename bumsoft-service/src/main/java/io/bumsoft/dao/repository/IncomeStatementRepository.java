package io.bumsoft.dao.repository;

import io.bumsoft.dao.entity.IncomeStatement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeStatementRepository extends JpaRepository<IncomeStatement, Long> {

}
