package io.bumsoft.mapper;

import io.bumsoft.dao.entity.Transaction;
import io.bumsoft.dto.common.TransactionDto;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        uses = {
                ReferenceEntityTypeMapper.class,
        }
)
public interface TransactionMapper extends AbstractObjectsMapper<Transaction, TransactionDto> {
}
