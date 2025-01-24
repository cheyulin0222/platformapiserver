package com.arplanets.platform.log.enums;

public enum OrganizationQuotaActionType implements ActionType{

    CREATE_ORGANIZATION_QUOTA("organization-quota.create"),
    GET_ORGANIZATION_QUOTA("organization-quota.get"),
    UPDATE_ORGANIZATION_QUOTA("organization-quota.update"),
    DELETE_ORGANIZATION_QUOTA("organization-quota.delete");

    private final String action;

    OrganizationQuotaActionType (String action) {
        this.action = action;
    }

    @Override
    public String getAction() {
        return action;
    }
}
