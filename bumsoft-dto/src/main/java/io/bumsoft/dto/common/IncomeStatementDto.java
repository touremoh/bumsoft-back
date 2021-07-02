package io.bumsoft.dto.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class IncomeStatementDto {
    private Long id;
    private String name;
    private Double expectedAmount;
    private String incomeType;
    private LocalDate lastUpdate;
}