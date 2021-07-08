package io.bumsoft.dto.common;

import io.bumsoft.dto.BumsoftResponse;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class IncomeStatementDto implements BumsoftResponse {
    private Long id;
    private String name;
    private Double expectedAmount;
    private ReferenceEntityTypeDto incomeType;
    private LocalDate updatedAt;
}