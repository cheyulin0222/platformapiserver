package com.arplanets.platform.log.enums;

public enum QuotaActionType implements ActionType{

    CREATE_QUOTA("quota.create"),
    GET_QUOTA("quota.get"),
    UPDATE_QUOTA("quota.update"),
    TOGGLE_QUOTA("quota.toggle"),
    DELETE_QUOTA("quota.delete");

    private final String action;

    QuotaActionType(String action) {
        this.action = action;
    }

    @Override
    public String getAction() {
        return action;
    }
}
