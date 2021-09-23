package io.bumsoft.dao.repository;

import io.bumsoft.dao.entity.AutomatedPlan;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AutomatedPlanRepository extends BumsoftRepository<AutomatedPlan, Long> {
    /**
     * find an active plan by account id
     * @param accountId
     * @return
     */
    Optional<AutomatedPlan> findByAccountIdAndIsActiveIsTrue(Long accountId);
}
