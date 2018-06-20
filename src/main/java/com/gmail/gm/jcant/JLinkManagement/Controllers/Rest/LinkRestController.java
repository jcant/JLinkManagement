package com.gmail.gm.jcant.JLinkManagement.Controllers.Rest;

import java.security.Principal;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gmail.gm.jcant.JLinkManagement.DomainRouting.JDomain;
import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLink;
import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLinkException;
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
    
    @RequestMapping(value = "/link/{id}", method = RequestMethod.POST)
    @JDomain(property = "frontend.domains")
    public ResponseEntity<Void> updateLink(@PathVariable(value = "id") long id, @RequestParam String target) throws JUserException, JLinkException {
    	
    	User authUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	//JUser dbUser = userService.getUserByLogin(authUser.getUsername());
    	
    	JLink link = linkService.findById(id);
    	
    	if ((!isHasRole("ROLE_ADMIN", authUser.getAuthorities())) && (!authUser.getUsername().equals(link.getUser().getLogin()))){
    		throw new JUserException("Access deny to change link of another user");
    	}
    	
    	link.setTarget(target);
    	linkService.updateLink(link);
    	
    	return new ResponseEntity<>(HttpStatus.CREATED);
    	//return "201 Location:/";
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
