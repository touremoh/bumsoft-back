package io.bumsoft.dto.common;

import io.bumsoft.dto.BumsoftDto;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseItem implements BumsoftDto {
    private String label;
    private Double expectedAmount;
    private Double actualAmount;
    private Double delta;
}
