package com.arplanets.platform.log.enums;

public enum ServiceManagementActionType implements ActionType {

    CREATE_SERVICE("service.create"),
    GET_SERVICE("service.get"),
    UPDATE_SERVICE("service.update"),
    DELETE_SERVICE("service.delete"),
    RECOVER_SERVICE("service.recover"),
    TOGGLE_SERVICE("service.toggle");

    private final String action;

    ServiceManagementActionType(String action) {
        this.action = action;
    }

    @Override
    public String getAction() {
        return action;
    }
}
