package org.example.zick.domain.user.exception;

import org.example.zick.global.error.exception.ErrorCode;
import org.example.zick.global.error.exception.ZickException;

public class UserNotFoundException extends ZickException {
    public static final ZickException EXCEPTION = new UserNotFoundException();
    public UserNotFoundException() {super(ErrorCode.USER_NOT_FOUND);}
}