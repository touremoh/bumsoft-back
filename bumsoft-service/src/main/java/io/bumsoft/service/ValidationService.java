package io.bumsoft.service;

import io.bumsoft.dao.entity.BumsoftEntity;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class ValidationService <E extends BumsoftEntity> {
    private final Validator validator;

    public Either<List<String>, Boolean> validate(E entity) {
        Set<ConstraintViolation<E>> violations = validator.validate(entity);
        if (!violations.isEmpty()) {
            List<String> errors = violations.stream()
                    .map(v -> v.getPropertyPath().toString().concat(":").concat(v.getMessage()))
                    .toList();
            return Either.left(errors);
        }
        return Either.right(Boolean.FALSE);
    }
}
