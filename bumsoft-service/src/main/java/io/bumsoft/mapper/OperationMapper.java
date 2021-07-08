package io.bumsoft.mapper;

import io.bumsoft.dao.entity.Operation;
import io.bumsoft.dto.common.OperationDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class OperationMapper implements AbstractObjectsMapper<Operation, OperationDto> {
}
