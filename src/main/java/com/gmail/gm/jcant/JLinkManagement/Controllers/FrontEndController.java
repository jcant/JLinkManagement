package com.gmail.gm.jcant.JLinkManagement.Controllers;

import com.gmail.gm.jcant.JLinkManagement.DomainRouting.JDomain;
import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLink;
import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLinkService;
import com.gmail.gm.jcant.JLinkManagement.JPA.LinkClick.JLinkClick;
import com.gmail.gm.jcant.JLinkManagement.JPA.LinkClick.JLinkClickService;
import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUser;
import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUserService;
import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUserRole;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/")
@JDomain(property = "frontend.domains")
public class FrontEndController {

    @Autowired
    private JUserService userService;
    @Autowired
	private JLinkService linkService;
    @Autowired
    private JLinkClickService linkClickService;
    @Autowired
    private PasswordEncoder encoder;

    @RequestMapping(value = "/") //main page
    public String index(Model model, Principal principal){

        if (principal != null) {

            User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            //User user = (User)principal;
            String login = user.getUsername();

            //String login = principal.getName();

            System.out.println("LOGIN = " + login);
            //JUser dbUser = userService.getUserByLogin(login);

            model.addAttribute("auth", true);
            model.addAttribute("login", login);
            model.addAttribute("roles", user.getAuthorities());
            //model.addAttribute("email", dbUser.getEmail());
        } else {
            model.addAttribute("auth", false);
            model.addAttribute("login", "NONAME!");
        }

        return "index";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestParam(required = false) String email, @RequestParam(required = false) String phone) {
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = user.getUsername();

        JUser dbUser = userService.getUserByLogin(login);
        dbUser.setEmail(email);

        userService.updateUser(dbUser);

        return "redirect:/";
    }

    @RequestMapping(value = "/newuser", method = RequestMethod.POST)
    public String update(@RequestParam String login,
                         @RequestParam String password,
                         @RequestParam(required = false) String email,
                         Model model) {
        if (userService.existsByLogin(login)) {
            model.addAttribute("exists", true);
            return "register";
        }

        String passHash = encoder.encode(password);

        JUser dbUser = new JUser(login, passHash, JUserRole.USER, email);
        userService.addUser(dbUser);

        return "redirect:/";
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/admin")
    public String admin(){
        return "admin";
    }

    @RequestMapping("/unauthorized")
    public String unauthorized(Model model){
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());
        return "unauthorized";
    }
    
    @RequestMapping("/links")
	public String links(Model model){
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = user.getUsername();

        JUser dbUser = userService.getUserByLogin(login);

        model.addAttribute("login", login);
        model.addAttribute("roles", user.getAuthorities());
        model.addAttribute("email", dbUser.getEmail());
        
        List<JLink> list = linkService.getLinksByUser(dbUser);
        
        System.out.println("LIST="+list);
        
        model.addAttribute("links", list);

        return "links";
    }

    @RequestMapping("/stats")
    public String stats(Model model){
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = user.getUsername();

        JUser dbUser = userService.getUserByLogin(login);

        model.addAttribute("login", login);
        model.addAttribute("roles", user.getAuthorities());
        model.addAttribute("email", dbUser.getEmail());

        List<JLinkClick> list = linkClickService.getByUser(dbUser);
        
        //list.forEach(n->System.out.println(n));

        //System.out.println("LIST="+list);

        model.addAttribute("linkLogs", list);

        return "stats";
    }
}
