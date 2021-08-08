package io.bumsoft.dao.repository;

import io.bumsoft.dao.entity.ReferenceEntityType;
import org.springframework.stereotype.Repository;

@Repository
public interface ReferenceEntityTypeRepository extends BumsoftRepository<ReferenceEntityType, Long> {

    ReferenceEntityType findByName(String name);
}
