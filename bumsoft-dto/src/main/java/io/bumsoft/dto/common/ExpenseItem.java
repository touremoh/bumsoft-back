package io.bumsoft.dto.common;

import io.bumsoft.dto.BumsoftResponse;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseItem implements BumsoftResponse {
    private String label;
    private Double expectedAmount;
    private Double actualAmount;
    private Double delta;
}
