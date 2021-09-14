package io.bumsoft.dto.response;

import io.bumsoft.dto.BumsoftResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorResponse implements BumsoftResponse {
    private ApiResponseCode responseCode;
    private String errorMessage;
}
