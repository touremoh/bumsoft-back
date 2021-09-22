package io.bumsoft.dao.specifications;

import io.bumsoft.dao.entity.BumsoftEntity;
import org.springframework.data.jpa.domain.Specification;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.Map;

import static java.util.Objects.nonNull;

public interface BumsoftQueryBuilder<E extends BumsoftEntity> {

    default String contains(String expression) {
        return MessageFormat.format("%{0}%", expression);
    }

    default Specification<E> like(String fieldName, String expression) {
        if (nonNull(expression)) {
            return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get(fieldName), contains(expression)));
        }
        return null;
    }

    default Specification<E> joinLike(String joinColumnName, String fieldName, String expression) {
        if (nonNull(expression)) {
            return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.join(joinColumnName).get(fieldName), contains(expression)));
        }
        return null;
    }

    default Specification<E> expressionEquals(String fieldName, String expression) {
        if (nonNull(expression)) {
            return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(fieldName), expression);
        }
        return null;
    }

    default Specification<E> between(String fieldName, LocalDate from, LocalDate to) {
        if (nonNull(from) && nonNull(to)) {
            return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.between(root.get(fieldName), from, to);
        }
        if (nonNull(from)) {
            return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get(fieldName), from);
        }
        if (nonNull(to)) {
            return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get(fieldName), to);
        }
        return null;
    }

    default Specification<E> expressionIsTrue(String fieldName, String expression) {
        if (nonNull(expression)) {
            return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(fieldName), Boolean.valueOf(expression));
        }
        return null;
    }

    default Specification<E> compareDouble(String fieldName, String expression, String comparator) {
        if (nonNull(expression) && nonNull(comparator)) {
            return switch (comparator) {
                case "gt" -> (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get(fieldName), Double.valueOf(expression));
                case "lt" -> (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThan(root.get(fieldName), Double.valueOf(expression));
                case "eq" -> (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(fieldName), Double.valueOf(expression));
                default -> null;
            };
        }
        return null;
    }

    default Specification<E> compareDate(String fieldName, LocalDate expression, String comparator) {
        if (nonNull(expression) && nonNull(comparator)) {
            return switch (comparator) {
                case "gt" -> (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get(fieldName), expression);
                case "lt" -> (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThan(root.get(fieldName), expression);
                case "eq" -> (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(fieldName), expression);
                default -> null;
            };
        }
        return null;
    }

    default Specification<E> compareInteger(String fieldName, String expression, String comparator) {
        if (nonNull(expression) && nonNull(comparator)) {
            return switch (comparator) {
                case "gt" -> (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get(fieldName), Integer.valueOf(expression));
                case "lt" -> (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThan(root.get(fieldName), Integer.valueOf(expression));
                case "eq" -> (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(fieldName), Integer.valueOf(expression));
                default -> null;
            };
        }
        return null;
    }

    Specification<E> buildQuerySpecifications(Map<String, String> queryCriteria);
}
