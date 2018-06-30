package com.gmail.gm.jcant.JLinkManagement.Helpers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;

import java.security.Principal;

public class JModelHelper {
    public static void prepareModel(Model model, Principal principal, String path) {
        if (principal != null) {
            User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            model.addAttribute("auth", true);
            model.addAttribute("admin", JRoleHelper.isHasRole("ROLE_ADMIN", user.getAuthorities()));
            model.addAttribute("login", user.getUsername());
            model.addAttribute("roles", user.getAuthorities());
            model.addAttribute("path", path);
        } else {
            model.addAttribute("auth", false);
            model.addAttribute("login", "NONAME!");
        }
    }
}
