package io.bumsoft.mapper;

import io.bumsoft.dao.entity.ReferenceEntityType;
import io.bumsoft.dto.common.ReferenceEntityTypeDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReferenceEntityTypeMapper {
    ReferenceEntityTypeDto toDto(ReferenceEntityType entity);
    ReferenceEntityType toEntity(ReferenceEntityTypeDto dto);
}
