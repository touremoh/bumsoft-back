package io.bumsoft.dto.common;

import io.bumsoft.dto.BumsoftDto;
import io.bumsoft.dto.BumsoftResponse;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncomeStatementDto implements BumsoftDto {
    private Long id;
    private String name;
    private Double expectedAmount;
    private ReferenceEntityTypeDto incomeType;
    private LocalDate updatedAt;
}