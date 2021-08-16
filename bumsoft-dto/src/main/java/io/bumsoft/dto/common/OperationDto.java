package io.bumsoft.dto.common;

import io.bumsoft.dto.BumsoftDto;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OperationDto implements BumsoftDto {
    private String name;
    private String description;
}
