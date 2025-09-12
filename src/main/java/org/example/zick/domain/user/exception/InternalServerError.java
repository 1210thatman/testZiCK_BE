package org.example.zick.domain.user.exception;

import org.example.zick.global.error.exception.ErrorCode;
import org.example.zick.global.error.exception.ZickException;

public class InternalServerError extends ZickException {
    public static final ZickException EXCEPTION =  new InternalServerError();
    private InternalServerError() {super(ErrorCode.INTERNAL_SERVER_ERROR);}
}