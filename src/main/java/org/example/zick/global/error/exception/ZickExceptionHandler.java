package org.example.zick.global.error.exception;

import org.example.zick.global.error.entity.ErrorResponseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ZickExceptionHandler {
    @ExceptionHandler(ZickException.class)
    protected ResponseEntity<ErrorResponseEntity> handleZickExcpetion(ZickException zickException){
        return ErrorResponseEntity.responseEntity(zickException.getErrorCode());
    }
}
