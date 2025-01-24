package com.arplanets.platform.exception.enums;

import com.arplanets.platform.exception.BusinessExceptionDisplay;

public enum PolicyErrorCode implements BusinessExceptionDisplay {

    _001("權限規則已存在"),
    _002("權限規則不存在");

    private final String message;

    PolicyErrorCode (String message) {
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
