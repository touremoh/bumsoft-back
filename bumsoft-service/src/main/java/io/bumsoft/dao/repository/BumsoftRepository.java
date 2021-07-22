package io.bumsoft.dao.repository;

import io.bumsoft.dao.entity.BumsoftEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BumsoftRepository<E extends BumsoftEntity, T> extends JpaRepository<E, T> {

}
