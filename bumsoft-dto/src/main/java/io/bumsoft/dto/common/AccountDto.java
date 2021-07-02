package io.bumsoft.dto.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class AccountDto {
    private Long id;
    private String name;
    private String accountType;
    private LocalDate lastUpdate;
    private List<TransactionDto> transactions;
}
