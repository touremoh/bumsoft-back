package io.bumsoft.dto.common;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class IncomeStatementDto {
    private Long id;
    private String name;
    private Double expectedAmount;
    private ReferenceEntityTypeDto incomeType;
    private LocalDate lastUpdate;
}