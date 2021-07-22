package io.bumsoft.mapper;

import io.bumsoft.dao.entity.ReferenceEntityType;
import io.bumsoft.dto.common.ReferenceEntityTypeDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReferenceEntityTypeMapper extends AbstractObjectsMapper<ReferenceEntityType, ReferenceEntityTypeDto> {

    default String mapReferenceEntityTypeToString(ReferenceEntityType source) {
        return source.getName();
    }

    default ReferenceEntityType mapStringToReferenceEntityType(String source) {
        ReferenceEntityType referenceEntityType = new ReferenceEntityType();
        referenceEntityType.setName(source);
        return referenceEntityType;
    }
}
