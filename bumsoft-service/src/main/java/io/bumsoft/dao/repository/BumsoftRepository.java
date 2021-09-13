package io.bumsoft.dao.repository;

import io.bumsoft.dao.entity.BumsoftEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BumsoftRepository<E extends BumsoftEntity, ID> extends JpaRepository<E, ID>, JpaSpecificationExecutor<E> {
}
