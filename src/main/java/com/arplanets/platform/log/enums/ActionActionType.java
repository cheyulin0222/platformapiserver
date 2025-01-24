package com.arplanets.platform.log.enums;

public enum ActionActionType implements ActionType{

    CREATE_ACTION("action.create"),
    GET_ACTION("action.get"),
    UPDATE_ACTION("action.update"),
    DELETE_ACTION("action.delete");

    private final String action;

    ActionActionType(String action) {
        this.action = action;
    }

    @Override
    public String getAction() {
        return action;
    }
}
