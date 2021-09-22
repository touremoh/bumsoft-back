package io.bumsoft.dao.specifications;

import io.bumsoft.annotations.BumsoftSpecification;
import io.bumsoft.dao.entity.Account;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

@BumsoftSpecification
public class AccountQueryBuilder implements BumsoftQueryBuilder<Account> {

    private static final String USER_ID="userId";
    private static final String ACCOUNT_NUMBER="accountNumber";
    private static final String ACCOUNT_NAME="name";
    private static final String DESCRIPTION="description";
    private static final String ACCOUNT_TYPE="accountType";
    private static final String ACCOUNT_TYPE_FIELD_NAME="name";

    @Override
    public Specification<Account> buildQuerySpecifications(Map<String, String> queryCriteria) {
        return Specification.
                where(like(ACCOUNT_NUMBER, queryCriteria.get(ACCOUNT_NUMBER)))
                .and(like(ACCOUNT_NAME, queryCriteria.get(ACCOUNT_NAME)))
                .and(like(DESCRIPTION, queryCriteria.get(DESCRIPTION)))
                .and(expressionEquals(USER_ID, queryCriteria.get(USER_ID)))
                .and(joinLike(ACCOUNT_TYPE, ACCOUNT_TYPE_FIELD_NAME, queryCriteria.get(ACCOUNT_TYPE)));
    }
}
