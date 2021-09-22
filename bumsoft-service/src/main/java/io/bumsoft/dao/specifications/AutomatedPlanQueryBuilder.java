package io.bumsoft.dao.specifications;

import io.bumsoft.annotations.BumsoftSpecification;
import io.bumsoft.dao.entity.AutomatedPlan;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

@BumsoftSpecification
public class AutomatedPlanQueryBuilder implements BumsoftQueryBuilder<AutomatedPlan> {
    private static final String USER_ID = "userId";
    private static final String ACCOUNT_ID = "accountId";
    private static final String ACCOUNT_IS_ACTIVE = "isActive";
    private static final String ACCOUNT_THRESHOLD = "threshold";
    private static final String THRESHOLD_COMPARATOR = "thresholdComparator";

    @Override
    public Specification<AutomatedPlan> buildQuerySpecifications(Map<String, String> queryCriteria) {
        return Specification
                .where(expressionEquals(USER_ID, queryCriteria.get(USER_ID)))
                .and(expressionEquals(ACCOUNT_ID, queryCriteria.get(ACCOUNT_ID)))
                .and(expressionIsTrue(ACCOUNT_IS_ACTIVE, queryCriteria.get(ACCOUNT_IS_ACTIVE)))
                .and(compareDouble(ACCOUNT_THRESHOLD, queryCriteria.get(ACCOUNT_THRESHOLD), THRESHOLD_COMPARATOR));
    }
}
