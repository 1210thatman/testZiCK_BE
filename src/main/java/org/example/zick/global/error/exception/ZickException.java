package org.example.zick.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ZickException extends  RuntimeException{
    private final ErrorCode errorCode;
}