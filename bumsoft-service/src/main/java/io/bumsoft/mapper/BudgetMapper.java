package io.bumsoft.mapper;

import io.bumsoft.dao.entity.Budget;
import io.bumsoft.dto.common.BudgetDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BudgetMapper extends AbstractObjectsMapper<Budget, BudgetDto> {
}
