package io.bumsoft.mapper;

import io.bumsoft.dao.entity.AutomatedPlan;
import io.bumsoft.dto.common.AutomatedPlanDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AutomatedPlanMapper extends AbstractObjectsMapper<AutomatedPlan, AutomatedPlanDto>{
}
