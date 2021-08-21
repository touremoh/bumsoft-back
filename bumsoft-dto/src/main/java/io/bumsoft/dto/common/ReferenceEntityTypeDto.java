package io.bumsoft.dto.common;

import io.bumsoft.dto.BumsoftDto;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReferenceEntityTypeDto implements BumsoftDto {
    private Long id;
    private String group;
    private String name;
}
