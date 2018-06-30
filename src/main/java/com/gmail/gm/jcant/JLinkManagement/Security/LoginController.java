package com.gmail.gm.jcant.JLinkManagement.Security;

import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUser;
import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUserException;
import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUserService;
import com.gmail.gm.jcant.JLinkManagement.JPA.UserLog.JUserLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/loginsuccess")
public class LoginController {
    @Autowired
    private JUserLogService userLogService;

    @Autowired
    private JUserService userService;

    @RequestMapping
    public String loginPage(HttpServletRequest request) throws JUserException {

        //System.out.println("We are in Login Controller!");

        //if (principal != null) {
        //System.out.println("principal != null");
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        JUser dbUser = userService.getUserByLogin(user.getUsername());

        userLogService.SaveUserComeIn(dbUser, request);
        //}
        return "redirect:/";
        //return "index";
    }
}
