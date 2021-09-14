package io.bumsoft.dto.response;

import lombok.Getter;

@Getter
public enum ApiResponseCode {
    OK(200, "Request successfully processed"),
    CREATED(201, "Resource successfully created"),
    NO_CONTENT(204, "Request successfully processed"),
    BAD_REQUEST(400, "Bad request"),
    RESOURCE_NOT_FOUND(404, "Resource not found"),
    INVALID_ID(404, "Invalid resource provided"),
    UPDATE_FAILED(500, "Unable to update resource"),
    CREATION_FAILED(500, "Unable to create resource"),;

    ApiResponseCode(int code, String reason) {
        this.code = code;
        this.reason = reason;
    }
    private final int code;
    private final String reason;
}
