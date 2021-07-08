package io.bumsoft.dto.common;

import io.bumsoft.dto.BumsoftResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ExpenseItem implements BumsoftResponse {
    private String label;
    private Double expectedValue;
    private Double actualValue;
    private Double remainingValue;
}
