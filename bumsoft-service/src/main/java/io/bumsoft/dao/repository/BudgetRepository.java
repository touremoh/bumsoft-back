package io.bumsoft.dao.repository;

import io.bumsoft.dao.entity.Budget;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudgetRepository extends BumsoftRepository<Budget, Long> {
    List<Budget> findByUserId(Long userId);
}
