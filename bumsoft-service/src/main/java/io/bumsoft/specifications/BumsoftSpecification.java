package io.bumsoft.specifications;

import io.bumsoft.dao.entity.BumsoftEntity;
import org.springframework.data.jpa.domain.Specification;

import java.text.MessageFormat;

import static java.util.Objects.nonNull;

public class BumsoftSpecification<E extends BumsoftEntity> {

    private static String contains(String expression) {
        return MessageFormat.format("%{0}%", expression);
    }

    public static <E> Specification<E> like(String fieldName, String expression) {
        if (nonNull(expression)) {
            return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get(fieldName), contains(expression)));
        }
        return null;
    }

    public static <E> Specification<E> joinLike(String joinColumnName, String fieldName, String expression) {
        if (nonNull(expression)) {
            return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.join(joinColumnName).get(fieldName), contains(expression)));
        }
        return null;
    }

    public static <E> Specification<E> equals(String fieldName, String expression) {
        if (nonNull(expression)) {
            return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(fieldName), expression);
        }
        return null;
    }
}
