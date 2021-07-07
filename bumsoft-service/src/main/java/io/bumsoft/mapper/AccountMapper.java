package io.bumsoft.mapper;

import io.bumsoft.dao.entity.Account;
import io.bumsoft.dto.common.AccountDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(
        componentModel = "spring",
        uses = { TransactionMapper.class }
)
public interface AccountMapper {

    @Mapping(target = "accountType", source = "accountType.name")
    @Mapping(target = "lastUpdate",  source = "updatedAt")
    AccountDto toDto(Account entity);


    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "deletedAt", ignore = true)
    })
    Account toEntity(AccountDto dto);
}
