package io.bumsoft.dto.common;

import io.bumsoft.dto.BumsoftResponse;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class AccountDto implements BumsoftResponse {
    private Long id;
    private String name;
    private LocalDate updatedAt;
    private String description;
    private ReferenceEntityTypeDto accountType;
    private List<TransactionDto> transactions;
}
