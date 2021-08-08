package io.bumsoft.mapper;

import io.bumsoft.dao.entity.Subscription;
import io.bumsoft.dto.common.SubscriptionDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper extends AbstractObjectsMapper<Subscription, SubscriptionDto> {
}
