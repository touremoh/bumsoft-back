package io.bumsoft.dao.repository;

import io.bumsoft.dao.entity.ReferenceEntityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReferenceEntityTypeRepository extends JpaRepository<ReferenceEntityType, Long> {

}
