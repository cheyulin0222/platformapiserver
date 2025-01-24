package com.arplanets.platform.exception.enums;

import com.arplanets.platform.exception.BusinessExceptionDisplay;

public enum UserErrorCode implements BusinessExceptionDisplay {

    _001("帳號已存在"),
    _002("帳號不存在");

    private final String message;

    UserErrorCode (String message) {
        this.message = message;
    }

    @Override
    public String message() {
        return message;
    }

    @Override
    public String description() {
        return this.getClass().getSimpleName()+this.name();
    }
}
