package io.bumsoft.mapper;

import io.bumsoft.dao.entity.PaymentInfo;
import io.bumsoft.dto.common.PaymentInfoDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class PaymentInfoMapper implements AbstractObjectsMapper<PaymentInfo, PaymentInfoDto> {
}
