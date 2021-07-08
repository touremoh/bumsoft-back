package io.bumsoft.mapper;

import io.bumsoft.dao.entity.ReferenceEntityType;
import io.bumsoft.dto.common.ReferenceEntityTypeDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ReferenceEntityTypeMapper implements AbstractObjectsMapper<ReferenceEntityType, ReferenceEntityTypeDto> {
}
