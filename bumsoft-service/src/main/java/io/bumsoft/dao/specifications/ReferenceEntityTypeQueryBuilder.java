package io.bumsoft.dao.specifications;

import io.bumsoft.annotations.BumsoftSpecification;
import io.bumsoft.dao.entity.ReferenceEntityType;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

@BumsoftSpecification
public class ReferenceEntityTypeQueryBuilder implements BumsoftQueryBuilder<ReferenceEntityType> {

    private static final String REF_TYP_GROUP="group";
    private static final String REF_TYP_NAME="name";

    @Override
    public Specification<ReferenceEntityType> buildQuerySpecifications(Map<String, String> queryCriteria) {
        return Specification.
                where(like(REF_TYP_GROUP, queryCriteria.get(REF_TYP_GROUP)))
                .and(like(REF_TYP_NAME, queryCriteria.get(REF_TYP_NAME)));
    }
}
