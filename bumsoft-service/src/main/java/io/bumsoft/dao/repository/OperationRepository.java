package io.bumsoft.dao.repository;

import io.bumsoft.dao.entity.Operation;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends BumsoftRepository<Operation, Long> {
}
