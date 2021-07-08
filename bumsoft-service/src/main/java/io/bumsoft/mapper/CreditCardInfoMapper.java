package io.bumsoft.mapper;

import io.bumsoft.dao.entity.CreditCardInfo;
import io.bumsoft.dto.common.CreditCardInfoDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class CreditCardInfoMapper implements AbstractObjectsMapper<CreditCardInfo, CreditCardInfoDto> {
}
