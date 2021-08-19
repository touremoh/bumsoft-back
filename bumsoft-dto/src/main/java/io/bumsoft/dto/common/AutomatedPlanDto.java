package io.bumsoft.dto.common;

import io.bumsoft.dto.BumsoftDto;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AutomatedPlanDto  implements BumsoftDto {
    private Long id;
    private Double threshold;
    private Boolean isActive;
    private Long userId;
    private Long accountId;
}
