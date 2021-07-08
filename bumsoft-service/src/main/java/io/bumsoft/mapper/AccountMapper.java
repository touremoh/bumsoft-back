package io.bumsoft.mapper;

import io.bumsoft.dao.entity.Account;
import io.bumsoft.dto.common.AccountDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class AccountMapper implements AbstractObjectsMapper<Account, AccountDto> {
}
