package com.gmail.gm.jcant.JLinkManagement.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLink;
import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLinkService;
import com.gmail.gm.jcant.JLinkManagement.JPA.User.JLinkUser;
import com.gmail.gm.jcant.JLinkManagement.JPA.User.JLinkUserService;

//@Controller
public class LinkListController {
//	@Autowired
//    private JLinkUserService userService;
//	@Autowired
//	private JLinkService linkService;
//	
//	@RequestMapping("/links")
//	public String links(Model model){
//        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String login = user.getUsername();
//
//        JLinkUser dbUser = userService.getUserByLogin(login);
//
//        model.addAttribute("login", login);
//        model.addAttribute("roles", user.getAuthorities());
//        model.addAttribute("email", dbUser.getEmail());
//        
//        List<JLink> list = linkService.getLinksByUser(dbUser);
//        
//        System.out.println("LIST="+list);
//        
//        model.addAttribute("links", list);
//
//        return "links";
//    }
	
}