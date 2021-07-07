package io.bumsoft.mapper;

import io.bumsoft.dao.entity.BumsoftUser;
import io.bumsoft.dto.common.BumsoftUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BumsoftUserMapper {

    @Mapping(target = "password", ignore = true)
    BumsoftUserDto toDto(BumsoftUser entity);


    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "accounts", ignore = true)
    @Mapping(target = "roles", ignore = true)
    BumsoftUser toEntity(BumsoftUserDto dto);
}
