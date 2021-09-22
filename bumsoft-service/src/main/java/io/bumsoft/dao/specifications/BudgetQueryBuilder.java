package io.bumsoft.dao.specifications;

import io.bumsoft.annotations.BumsoftSpecification;
import io.bumsoft.dao.entity.Budget;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

@BumsoftSpecification
public class BudgetQueryBuilder implements BumsoftQueryBuilder<Budget> {

    private static final String BDGT_NAME = "name";
    private static final String BDGT_AMOUNT = "amount";
    private static final String BDGT_AMOUNT_CMP = "amountComparator";
    private static final String BDGT_USER_ID = "userId";
    private static final String BDGT_ACC_ID = "accountId";

    @Override
    public Specification<Budget> buildQuerySpecifications(Map<String, String> queryCriteria) {
        return Specification
                .where(like(BDGT_NAME, queryCriteria.get(BDGT_NAME)))
                .and(compareDouble(BDGT_AMOUNT, queryCriteria.get(BDGT_AMOUNT), BDGT_AMOUNT_CMP))
                .and(expressionEquals(BDGT_USER_ID, queryCriteria.get(BDGT_USER_ID)))
                .and(expressionEquals(BDGT_ACC_ID, queryCriteria.get(BDGT_ACC_ID)));
    }
}
