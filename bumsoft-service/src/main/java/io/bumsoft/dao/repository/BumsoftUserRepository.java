package io.bumsoft.dao.repository;

import io.bumsoft.dao.entity.BumsoftUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BumsoftUserRepository extends JpaRepository<BumsoftUser, Long> {

}
