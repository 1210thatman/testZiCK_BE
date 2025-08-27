package org.example.zick.domain.student.exception;

import org.example.zick.global.error.exception.ErrorCode;
import org.example.zick.global.error.exception.ZickException;

public class KeyNotFoundException extends ZickException {
    public static final ZickException EXCEPTION = new KeyNotFoundException();
    public KeyNotFoundException() {super(ErrorCode.KEY_NOT_FOUND);}
}