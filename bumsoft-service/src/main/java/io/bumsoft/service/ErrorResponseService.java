package io.bumsoft.service;

import io.bumsoft.dto.response.ApiResponseCode;
import io.bumsoft.dto.response.ErrorResponse;

public class ErrorResponseService <ID> {

    private ErrorResponseService() {}

    public static ErrorResponse creationFailed(String message) {
        return ErrorResponse
                .builder()
                .responseCode(ApiResponseCode.CREATION_FAILED)
                .errorMessage(ApiResponseCode.CREATION_FAILED.getReason() + " - " + message)
                .build();
    }

    public static <ID> ErrorResponse invalidId(ID id) {
        return ErrorResponse
                .builder()
                .responseCode(ApiResponseCode.INVALID_ID)
                .errorMessage(ApiResponseCode.INVALID_ID.getReason() + " - ID [" + id + "]")
                .build();
    }

    public static ErrorResponse updateFailed(String message) {
        return ErrorResponse
                .builder()
                .responseCode(ApiResponseCode.UPDATE_FAILED)
                .errorMessage(ApiResponseCode.INVALID_ID.getReason() + " - " + message)
                .build();
    }

    public static <ID> ErrorResponse deleteFailed(ID id) {
        return invalidId(id);
    }

    public static ErrorResponse resourceNotFound(String message) {
        return ErrorResponse
                .builder()
                .responseCode(ApiResponseCode.RESOURCE_NOT_FOUND)
                .errorMessage(ApiResponseCode.RESOURCE_NOT_FOUND.getReason() + " - " + message)
                .build();
    }
}
