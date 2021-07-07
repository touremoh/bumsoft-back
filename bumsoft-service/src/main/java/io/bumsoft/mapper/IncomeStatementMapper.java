package io.bumsoft.mapper;

import io.bumsoft.dao.entity.IncomeStatement;
import io.bumsoft.dto.common.IncomeStatementDto;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = ReferenceEntityTypeMapper.class
)
public interface IncomeStatementMapper {

    @Mapping(target = "lastUpdate", source = "updatedAt")
    IncomeStatementDto toDto(IncomeStatement entity);

    @InheritConfiguration
    IncomeStatement toEntity(IncomeStatementDto dto);


}
