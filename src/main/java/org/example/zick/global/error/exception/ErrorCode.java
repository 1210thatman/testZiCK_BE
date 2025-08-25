package org.example.zick.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {
    INTERNAL_SERVER_ERROR("internalServerError", 500),
    BAD_REQUEST_VALIDATION("validationError", 400),
    USER_NOT_FOUND("userNotFound", 404),
    QR_EXPIRED("qrExpired", 401);
    private final String type;
    private final int statusCode;
}