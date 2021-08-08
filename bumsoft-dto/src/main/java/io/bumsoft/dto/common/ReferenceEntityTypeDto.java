package io.bumsoft.dto.common;

import io.bumsoft.dto.BumsoftDto;
import io.bumsoft.dto.BumsoftResponse;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReferenceEntityTypeDto implements BumsoftDto {
    private String group;
    private String name;
}
