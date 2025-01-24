package com.arplanets.platform.exception.enums;

import com.arplanets.platform.exception.BusinessExceptionDisplay;

public enum ServiceManagementErrorCode implements BusinessExceptionDisplay {

    _001("服務已存在"),
    _002("服務不存在");

    private final String message;

    ServiceManagementErrorCode (String message) {
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
