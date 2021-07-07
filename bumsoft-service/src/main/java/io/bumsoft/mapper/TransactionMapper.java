package io.bumsoft.mapper;

import io.bumsoft.dao.entity.Transaction;
import io.bumsoft.dto.common.TransactionDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(target = "transactionValue",    source = "value")
    @Mapping(target = "accountId",           source = "relatedAccount.id")
    @Mapping(target = "transactionType",     source = "transactionType.name")
    @Mapping(target = "incomeStatementName", source = "incomeStatement.name")
    TransactionDto toDto(Transaction entity);


    @InheritInverseConfiguration
    Transaction toEntity(TransactionDto dto);
}
