package io.bumsoft.mapper;

import io.bumsoft.dao.entity.IncomeStatement;
import io.bumsoft.dto.common.IncomeStatementDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IncomeStatementMapper extends AbstractObjectsMapper<IncomeStatement, IncomeStatementDto> {
}
