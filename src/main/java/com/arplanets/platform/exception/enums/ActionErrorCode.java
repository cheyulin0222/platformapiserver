package com.arplanets.platform.exception.enums;

import com.arplanets.platform.exception.BusinessExceptionDisplay;

public enum ActionErrorCode implements BusinessExceptionDisplay {

    _001("服務操作行為已存在"),
    _002("服務操作行為不存在");

    private final String message;

    ActionErrorCode (String message) {
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
