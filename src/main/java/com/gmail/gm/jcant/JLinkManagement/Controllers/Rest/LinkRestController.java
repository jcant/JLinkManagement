package com.gmail.gm.jcant.JLinkManagement.Controllers.Rest;

import java.security.Principal;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gmail.gm.jcant.JLinkManagement.DomainRouting.JDomain;
import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLink;
import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLinkService;
import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUser;
import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUserException;
import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUserService;

@RestController
public class LinkRestController {

    @Autowired
    private JLinkService linkService;
    @Autowired
    private JUserService userService;

    @RequestMapping(value = "/links/{user}")
    @JDomain(property = "frontend.domains")
    public List<JLink> getLinks(@PathVariable(value = "user") String user, Principal principal) throws JUserException{
        
    	JUser dbUser = userService.getUserByLogin(user);
    	User authUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	
    	if ((!isHasRole("ROLE_ADMIN", authUser.getAuthorities())) && (!authUser.getUsername().equals(user))){
    		throw new JUserException("Access deny to get links for another user");
    	}

    	return linkService.findByUser(dbUser);
    }
    
    private boolean isHasRole(String role, Collection<GrantedAuthority> gaCollection) {
    	boolean result = false;
    	Iterator<GrantedAuthority> grIt = gaCollection.iterator();
    	while(grIt.hasNext()) {
    		GrantedAuthority ga = grIt.next();
    		if(ga.getAuthority().equals(role)) {
    			result = true;
    		}
    	}
    	
    	return result;
    }
}
