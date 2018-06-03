package com.gmail.gm.jcant.JLinkManagement.JPA.User;

public enum JlinkUserRole {
    ADMIN, USER;

    @Override
    public String toString() {
        return "ROLE_" + name();
    }
}
