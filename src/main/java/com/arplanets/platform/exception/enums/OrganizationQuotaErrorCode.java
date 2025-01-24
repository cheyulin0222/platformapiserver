package com.arplanets.platform.exception.enums;

import com.arplanets.platform.exception.BusinessExceptionDisplay;

public enum OrganizationQuotaErrorCode implements BusinessExceptionDisplay {

    _001("組織服務額度已存在"),
    _002("組織服務額度不存在");

    private final String message;

    OrganizationQuotaErrorCode (String message) {
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
