package io.bumsoft.mapper;

import io.bumsoft.dao.entity.Account;
import io.bumsoft.dao.entity.ReferenceEntityType;
import io.bumsoft.dao.entity.Transaction;
import io.bumsoft.dto.common.TransactionDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class TransactionMapperTest {

    @InjectMocks
    TransactionMapperImpl mapper;

    @Mock
    ReferenceEntityTypeMapperImpl typeMapper;

    @Test
    public void given_entity_when_mapped_then_dto_returned() {
        // given
        Transaction entity = buildEntity(10000L);

        // when
        when(typeMapper.mapReferenceEntityTypeToString(any())).thenReturn("TypeName");
        TransactionDto dto = mapper.toDto(entity);

        // then
        assertThat(dto).isNotNull();
        assertThat(dto.getValue()).isEqualTo(152.59);
        assertThat(dto.getDescription()).isEqualTo("Description");
        assertThat(dto.getProcessingDate().getYear()).isEqualTo(LocalDate.now().getYear());
        assertThat(dto.getTransactionType()).isEqualTo("TypeName");
    }

    @Test
    public void given_dto_when_mapped_then_entity_returned() {
        // given
        TransactionDto dto = buildDto(10L);
        // when
        when(typeMapper.mapStringToReferenceEntityType(any())).thenReturn(buildTransactionType(1000L));
        Transaction entity = mapper.toEntity(dto);
        // then
        assertThat(entity).isNotNull();
        assertThat(entity.getValue()).isEqualTo(10.5);
        assertThat(entity.getDescription()).isEqualTo("Description");
        assertThat(entity.getProcessingDate().getYear()).isEqualTo(LocalDate.now().getYear());
        assertThat(entity.getTransactionType().getName()).isEqualTo("TypeName");
    }

    public Transaction buildEntity(Long id) {
        Transaction entity = new Transaction();

        entity.setId(id);
        entity.setValue(152.59);
        entity.setDescription("Description");
        entity.setProcessingDate(LocalDate.now());
        entity.setRelatedAccount(buildAccount(1000L));
        entity.setTransactionType(buildTransactionType(1000L));

        return entity;
    }

    protected TransactionDto buildDto(long id) {
        return TransactionDto
                .builder()
                    .id(id)
                    .transactionType("TypeName")
                    .value(10.5)
                    .processingDate(LocalDate.now())
                    .description("Description")
                .build();
    }

    public Account buildAccount(Long id) {
        Account account = new Account();
        account.setId(id);
        account.setName("Account");
        account.setDescription("Description");
        return account;
    }

    public ReferenceEntityType buildTransactionType(Long id) {
        ReferenceEntityType type = new ReferenceEntityType();
        type.setId(id);
        type.setName("TypeName");
        return type;
    }
}
