package com.aks.billingserv.enums;

public enum AccountStatus {
    CREATED("created"),DELETED("deleted"),ACTIVE("active");

    String value;
    AccountStatus(String string) {
        this.value=string;
    }
    public String getValue() {
        return value;
    }
}
