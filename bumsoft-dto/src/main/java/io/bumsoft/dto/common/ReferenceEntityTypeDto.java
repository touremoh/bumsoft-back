package io.bumsoft.dto.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ReferenceEntityTypeDto {
    private Long id;
    private String group;
    private String name;
    private String description;
}
