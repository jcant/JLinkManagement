package com.gmail.gm.jcant.JLinkManagement.Helpers;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JRoleHelper {
    public static boolean isHasRole(String role, Collection<GrantedAuthority> gaCollection) {
        boolean result = false;
        for (GrantedAuthority ga : gaCollection) {
            if (ga.getAuthority().equals(role)) {
                result = true;
            }
        }

        return result;
    }
}
