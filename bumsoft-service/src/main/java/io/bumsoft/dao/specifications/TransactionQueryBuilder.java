package io.bumsoft.dao.specifications;

import io.bumsoft.annotations.BumsoftSpecification;
import io.bumsoft.dao.entity.Transaction;
import io.bumsoft.helper.BumsoftUtils;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.Map;

@BumsoftSpecification
public class TransactionQueryBuilder implements BumsoftQueryBuilder<Transaction> {

    private static final String TRANSACTION_VALUE = "value";
    private static final String TRANSACTION_VALUE_COMPARATOR = "valueComparator";
    private static final String TRANSACTION_DATE = "processingDate";
    private static final String TRANSACTION_DATE_COMPARATOR = "processingDateComparator";

    private static final String DESCRIPTION = "description";
    private static final String RELATED_ACCOUNT = "relatedAccountId";
    private static final String TRANSACTION_TYPE = "transactionType";
    private static final String TRANSACTION_TYPE_NAME = "name";

    @Override
    public Specification<Transaction> buildQuerySpecifications(Map<String, String> queryCriteria) {
        LocalDate processingDate = BumsoftUtils.toLocalDate(queryCriteria.get(TRANSACTION_DATE), "yyyy-MM-dd");
        return Specification
                .where(compareDate(TRANSACTION_DATE, processingDate, queryCriteria.get(TRANSACTION_DATE_COMPARATOR)))
                .and(compareDouble(TRANSACTION_VALUE, queryCriteria.get(TRANSACTION_VALUE), TRANSACTION_VALUE_COMPARATOR))
                .and(like(DESCRIPTION, queryCriteria.get(DESCRIPTION)))
                .and(expressionEquals(RELATED_ACCOUNT, queryCriteria.get(RELATED_ACCOUNT)))
                .and(joinLike(TRANSACTION_TYPE, TRANSACTION_TYPE_NAME, queryCriteria.get(TRANSACTION_TYPE)));
    }
}
