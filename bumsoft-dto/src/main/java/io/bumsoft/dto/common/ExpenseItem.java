package io.bumsoft.dto.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ExpenseItem {
    private String label;
    private Double expectedValue;
    private Double actualValue;
    private Double remainingValue;
}
