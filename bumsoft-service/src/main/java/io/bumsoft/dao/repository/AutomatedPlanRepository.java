package io.bumsoft.dao.repository;

import io.bumsoft.dao.entity.AutomatedPlan;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutomatedPlanRepository extends BumsoftRepository<AutomatedPlan, Long> {
    List<AutomatedPlan> findByUserIdAndIsActiveIsTrue(Long userId);
}
