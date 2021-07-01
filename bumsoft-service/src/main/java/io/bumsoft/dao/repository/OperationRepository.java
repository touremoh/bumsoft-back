package io.bumsoft.dao.repository;

import io.bumsoft.dao.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Long> {
}
