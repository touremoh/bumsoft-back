package io.bumsoft.service;

import io.bumsoft.dao.entity.BumsoftEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class ValidationService <E extends BumsoftEntity> {
    private final Validator validator;

    public boolean notValid(E entity) {
       return !validator.validate(entity).isEmpty();
    }

    public String getValidationErrorMessage(E entity) {
        Set<ConstraintViolation<E>> violations = validator.validate(entity);
        Map<String, String> messages = new HashMap<>();

        for (ConstraintViolation<E> v : violations) {
            log.info(v.toString());
            messages.put(v.getPropertyPath().toString(), v.getMessage());
        }
        return messages.toString();
    }
}
