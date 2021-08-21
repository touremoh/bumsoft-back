package io.bumsoft.dto.common;

import io.bumsoft.dto.BumsoftDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto implements BumsoftDto {
    private Long id;
    private Long userId;
    private String accountNumber;
    private String name;
    private String description;
    private String accountType;
    private List<TransactionDto> transactions;
}
