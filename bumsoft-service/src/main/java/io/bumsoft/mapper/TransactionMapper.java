package io.bumsoft.mapper;

import io.bumsoft.dao.entity.Transaction;
import io.bumsoft.dto.common.TransactionDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class TransactionMapper implements AbstractObjectsMapper<Transaction, TransactionDto> {
}
