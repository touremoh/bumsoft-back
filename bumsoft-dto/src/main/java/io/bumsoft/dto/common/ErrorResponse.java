package io.bumsoft.dto.common;

import io.bumsoft.dto.BumsoftResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorResponse implements BumsoftResponse {
    private String errorMessage;
    private String errorReason;
}
