package io.bumsoft.mapper;

import io.bumsoft.dao.entity.Role;
import io.bumsoft.dto.common.RoleDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class RoleMapper implements AbstractObjectsMapper<Role, RoleDto> {
}
