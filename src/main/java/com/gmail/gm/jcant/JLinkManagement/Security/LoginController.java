package com.gmail.gm.jcant.JLinkManagement.Security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
    @RequestMapping
    public String loginPage() {
        return "redirect:/";
        //return "index";
    }
}
