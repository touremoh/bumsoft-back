package io.bumsoft.mapper;

import io.bumsoft.dao.entity.BumsoftUser;
import io.bumsoft.dto.common.BumsoftUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class BumsoftUserMapper implements AbstractObjectsMapper<BumsoftUser, BumsoftUserDto> {

    @Override
    public BumsoftUserDto toDto(BumsoftUser entity) {
        return this.toDtoIgnoringPassword(entity);
    }
    @Mapping(target = "password", ignore = true)
    abstract BumsoftUserDto toDtoIgnoringPassword(BumsoftUser entity);
}
