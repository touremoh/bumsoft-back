package io.bumsoft.dto.common;

import io.bumsoft.dto.BumsoftResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class RoleDto implements BumsoftResponse {
    private String name;
    private String description;
    private List<OperationDto> operations;
}
