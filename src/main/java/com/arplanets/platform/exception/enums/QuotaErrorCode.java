package com.arplanets.platform.exception.enums;

import com.arplanets.platform.exception.BusinessExceptionDisplay;

public enum QuotaErrorCode implements BusinessExceptionDisplay {

    _001("服務額度已存在"),
    _002("服務額度不存在"),
    _003("服務額度已啟用"),
    _004("服務額度已停用");

    private final String message;

    QuotaErrorCode (String message) {
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
