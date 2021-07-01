package io.bumsoft.dao.repository;

import io.bumsoft.dao.entity.Account;
import io.bumsoft.dao.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
