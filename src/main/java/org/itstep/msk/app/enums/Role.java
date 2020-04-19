package org.itstep.msk.app.enums;

public enum Role {
    ROLE_WAITER("Официант"),
    ROLE_ADMIN("Администратор"),
    ROLE_MANAGER("Менеджер");

    private String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
