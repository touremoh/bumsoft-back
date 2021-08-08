package io.bumsoft.dto.common;

import io.bumsoft.dto.BumsoftDto;
import io.bumsoft.dto.BumsoftResponse;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto implements BumsoftDto {
    private Long id;
    private String name;
    private LocalDate updatedAt;
    private String description;
    private String accountType;
    private List<TransactionDto> transactions;
}
