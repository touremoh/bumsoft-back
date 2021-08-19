package io.bumsoft.dto.common;

import io.bumsoft.dto.BumsoftDto;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BudgetDto implements BumsoftDto {
    private Long id;
    private String name;
    private Double amount;
    private Long userId;
    private Long accountId;
}
