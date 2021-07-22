package io.bumsoft.mapper;

import io.bumsoft.dao.entity.Account;
import io.bumsoft.dao.entity.ReferenceEntityType;
import io.bumsoft.dto.common.AccountDto;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        uses = {
                TransactionMapper.class,
                ReferenceEntityTypeMapper.class,
                BumsoftUserMapper.class
        }
 )
public interface AccountMapper extends AbstractObjectsMapper<Account, AccountDto> {
}
