package io.bumsoft.dao.repository;

import io.bumsoft.dao.entity.Budget;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepository extends BumsoftRepository<Budget, Long> {
}
