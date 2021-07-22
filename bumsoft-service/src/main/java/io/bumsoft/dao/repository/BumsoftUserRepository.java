package io.bumsoft.dao.repository;

import io.bumsoft.dao.entity.BumsoftUser;
import org.springframework.stereotype.Repository;

@Repository
public interface BumsoftUserRepository extends BumsoftRepository<BumsoftUser, Long> {

}
