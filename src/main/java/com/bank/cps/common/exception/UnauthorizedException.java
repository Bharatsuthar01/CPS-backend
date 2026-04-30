package com.bank.cps.common.exception;

public class UnauthorizedException extends BusinessException {
    public UnauthorizedException(String message) {
        super("UNAUTHORIZED", message);
    }
}
