package com.gmail.gm.jcant.JLinkManagement.Controllers;

import com.gmail.gm.jcant.JLinkManagement.DomainRouting.JDomain;
import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLink;
import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLinkService;
import com.gmail.gm.jcant.JLinkManagement.JPA.User.JLinkUser;
import com.gmail.gm.jcant.JLinkManagement.JPA.User.JLinkUserService;
import com.gmail.gm.jcant.JLinkManagement.JPA.User.JlinkUserRole;

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
    private JLinkUserService userService;
    @Autowired
	private JLinkService linkService;
    @Autowired
    private PasswordEncoder encoder;

    @RequestMapping(value = "/")
    //@JDomain(property = "frontend.domains")
    public String index(Model model){

        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = user.getUsername();

        JLinkUser dbUser = userService.getUserByLogin(login);

        model.addAttribute("login", login);
        model.addAttribute("roles", user.getAuthorities());
        model.addAttribute("email", dbUser.getEmail());

        return "index";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    //@JDomain(property = "frontend.domains")
    public String update(@RequestParam(required = false) String email, @RequestParam(required = false) String phone) {
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = user.getUsername();

        JLinkUser dbUser = userService.getUserByLogin(login);
        dbUser.setEmail(email);

        userService.updateUser(dbUser);

        return "redirect:/";
    }

    @RequestMapping(value = "/newuser", method = RequestMethod.POST)
    //@JDomain(property = "frontend.domains")
    public String update(@RequestParam String login,
                         @RequestParam String password,
                         @RequestParam(required = false) String email,
                         Model model) {
        if (userService.existsByLogin(login)) {
            model.addAttribute("exists", true);
            return "register";
        }

        String passHash = encoder.encode(password);

        JLinkUser dbUser = new JLinkUser(login, passHash, JlinkUserRole.USER, email);
        userService.addUser(dbUser);

        return "redirect:/";
    }

    @RequestMapping("/register")
    //@JDomain(property = "frontend.domains")
    public String register() {
        return "register";
    }

    @RequestMapping("/admin")
    //@JDomain(property = "frontend.domains")
    public String admin(){
        return "admin";
    }

    @RequestMapping("/unauthorized")
    //@JDomain(property = "frontend.domains")
    public String unauthorized(Model model){
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());
        return "unauthorized";
    }
    
    @RequestMapping("/links")
    //@JDomain(property = "frontend.domains")
	public String links(Model model){
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = user.getUsername();

        JLinkUser dbUser = userService.getUserByLogin(login);

        model.addAttribute("login", login);
        model.addAttribute("roles", user.getAuthorities());
        model.addAttribute("email", dbUser.getEmail());
        
        List<JLink> list = linkService.getLinksByUser(dbUser);
        
        System.out.println("LIST="+list);
        
        model.addAttribute("links", list);

        return "links";
    }
}
