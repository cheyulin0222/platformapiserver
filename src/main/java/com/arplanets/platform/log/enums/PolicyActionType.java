package com.arplanets.platform.log.enums;

public enum PolicyActionType implements ActionType{

    CREATE_POLICY("policy.create"),
    GET_POLICY("policy.get"),
    UPDATE_POLICY("policy.update"),
    DELETE_POLICY("policy.delete");

    private final String action;

    PolicyActionType(String action) {
        this.action = action;
    }

    @Override
    public String getAction() {
        return action;
    }
}
