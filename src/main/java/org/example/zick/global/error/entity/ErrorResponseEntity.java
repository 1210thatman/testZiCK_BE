package org.example.zick.global.error.entity;

import lombok.Builder;
import lombok.Getter;
import org.example.zick.global.error.exception.ErrorCode;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
public class ErrorResponseEntity {
    private int statusCode;
    private String type;

    public static ResponseEntity<ErrorResponseEntity> responseEntity(ErrorCode errorCode){
        return ResponseEntity
                .status(errorCode.getStatusCode())
                .body(ErrorResponseEntity.builder()
                        .statusCode(errorCode.getStatusCode())
                        .type(errorCode.getType())
                        .build());
    }
}
