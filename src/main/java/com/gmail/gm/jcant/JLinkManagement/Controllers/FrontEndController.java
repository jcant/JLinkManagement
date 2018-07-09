package com.gmail.gm.jcant.JLinkManagement.Controllers;

import com.gmail.gm.jcant.JLinkManagement.DomainRouting.JDomain;
import com.gmail.gm.jcant.JLinkManagement.Helpers.JModelHelper;
import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUser;
import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUserException;
import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUserService;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "/")
@JDomain(property = "frontend.domains")
public class FrontEndController {

    @Autowired
    private JUserService userService;
    
    //main page
    @RequestMapping(value = "/")
    public String index(Model model, Principal principal){

        JModelHelper.prepareModel(model, principal, "");

        return "index";
    }
    
    @RequestMapping(value = "/profile")
    public String profile(Model model, Principal principal) throws JUserException{

        JModelHelper.prepareModel(model, principal, "profile");
            
        JUser dbUser = userService.getUserByLogin(principal.getName());

        if (dbUser.isResetPassword()){
            model.addAttribute("resetPassword", true);
        }

        model.addAttribute("name", dbUser.getName());
        model.addAttribute("email", dbUser.getEmail());
        model.addAttribute("userId", dbUser.getId());

        return "profile";
    }
    
    @RequestMapping(value = "/freelinks")
    public String freelinks(Model model, Principal principal){

        JModelHelper.prepareModel(model, principal, "freelinks");
    	
        return "freelinks";
    }
    
    @RequestMapping(value = "/links")
    public String links(Model model, Principal principal){

        JModelHelper.prepareModel(model, principal, "links");
    	
        return "links";
    }

    @RequestMapping("/stats")
    public String stats(Model model, Principal principal) {

        JModelHelper.prepareModel(model, principal, "stats");
    	
        return "stats";
    }
    
    @RequestMapping("/articles")
    public String articles(Model model, Principal principal) {

        JModelHelper.prepareModel(model, principal, "articles");
    	
        return "articles";
    }

    @RequestMapping("/promo")
    public String promo(Model model, Principal principal) {

        JModelHelper.prepareModel(model, principal, "adv");

        return "promo";
    }

    @RequestMapping("/rootlinks")
    public String rootlinks(Model model, Principal principal) {

        JModelHelper.prepareModel(model, principal, "rootlinks");

        return "rootlinks";
    }
    
    @RequestMapping("/users")
    public String users(Model model, Principal principal) {

        JModelHelper.prepareModel(model, principal, "users");

        return "users";
    }
    
//    @RequestMapping("/error")
//    public String error(/*Model model, Principal principal*/) {
//
//        //JModelHelper.prepareModel(model, principal, "users");
//
//        return "error";
//    }
    
}
