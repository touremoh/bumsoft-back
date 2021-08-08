package io.bumsoft.dto.common;

import io.bumsoft.dto.BumsoftResponse;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse implements BumsoftResponse {
    private String errorMessage;
    private String errorReason;
}
