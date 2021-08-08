package io.bumsoft.mapper;

import io.bumsoft.dao.entity.Account;
import io.bumsoft.dao.entity.IncomeStatement;
import io.bumsoft.dao.entity.Transaction;
import io.bumsoft.dto.common.TransactionDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = {
                ReferenceEntityTypeMapper.class
        }
)
public interface TransactionMapper extends AbstractObjectsMapper<Transaction, TransactionDto> {

        @Mapping(target = "relatedAccount", source = "relatedAccountId")
        @Mapping(target = "incomeStatement", source = "incomeStatementId")
        Transaction toEntity(TransactionDto source);

        @InheritInverseConfiguration
        TransactionDto toDto(Transaction source);

        default Account mapToAccount(Long relatedAccountId) {
                Account account = new Account();
                account.setId(relatedAccountId);
                return account;
        }

        default IncomeStatement mapToIncomeStatement(Long incomeStatementId) {
                IncomeStatement incomeStatement = new IncomeStatement();
                incomeStatement.setId(incomeStatementId);
                return incomeStatement;
        }

        default Long mapToAccountId(Account account) {
                return account.getId();
        }

        default Long mapToIncomeStatementId(IncomeStatement incomeStatement) {
                return incomeStatement.getId();
        }
}
