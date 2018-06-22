package com.gmail.gm.jcant.JLinkManagement.Controllers.Rest;

import java.security.Principal;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.naming.LinkException;

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

import com.gmail.gm.jcant.JDate;
import com.gmail.gm.jcant.JLinkManagement.DomainRouting.JDomain;
import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLink;
import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLinkException;
import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLinkService;
import com.gmail.gm.jcant.JLinkManagement.JPA.RootLink.JRootLink;
import com.gmail.gm.jcant.JLinkManagement.JPA.RootLink.JRootLinkService;
import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUser;
import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUserException;
import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUserService;

@RestController
public class LinkRestController {

    @Autowired
    private JLinkService linkService;
    @Autowired
    private JRootLinkService rootLinkService;
    @Autowired
    private JUserService userService;

    //get All user Links
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
    
    //update Link "target"
    @RequestMapping(value = "/link/{id}", method = RequestMethod.POST)
    @JDomain(property = "frontend.domains")
    public ResponseEntity<Void> updateLink(@PathVariable(value = "id") long id, @RequestParam String target, @RequestParam boolean enabled) throws JUserException, JLinkException {
    	
    	User authUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	
    	JLink link = linkService.findById(id);
    	
    	if ((!isHasRole("ROLE_ADMIN", authUser.getAuthorities())) && (!authUser.getUsername().equals(link.getUser().getLogin()))){
    		throw new JUserException("Access deny to change link of another user");
    	}
    	
    	link.setTarget(target);
    	link.setEnabled(enabled);
    	linkService.updateLink(link);
    	
    	return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    //check is Link free
    @RequestMapping(value = "/link/check", method = RequestMethod.POST)
    @JDomain(property = "frontend.domains")
    public boolean isFree(@RequestParam String url) {
    	if (linkService.existsByUrl(url)) {
    		return false;
    	}else {
    		return true;
    	}
    }
    
    //add new Link
    @RequestMapping(value = "/link/add", method = RequestMethod.POST)
    @JDomain(property = "frontend.domains")
    public ResponseEntity<Void> addLink(@RequestParam String rootUrl, @RequestParam String userPart, @RequestParam String mode, @RequestParam String target, Principal principal) throws LinkException, JUserException{
    	
    	JUser user = userService.getUserByLogin(principal.getName());
    	
    	JRootLink rootLink = rootLinkService.getRootLinkByUrl(rootUrl);
    	if (rootLink == null) {
    		throw new LinkException("Cant't create new Link: base URL is invalid!");
    	}
    	
    	if ((isContainSpecialSymbols(userPart, true))||(isContainSpecialSymbols(target, false))) {
    		throw new LinkException("Cant't create new Link: selected parameters contain special symbols!");
    	}
    	
    	String url = "";
    	if (mode.equals("subdomain")) {
    		url = "http://" + userPart + "." + rootUrl + "/"; 
    	}
    	if (mode.equals("parameter")) {
    		url = "http://" + rootUrl + "/" + userPart; 
    	}
    	
    	if (linkService.existsByUrl(url)) {
    		//System.out.println("********************* url="+url);
    		//System.out.println("********************* mode="+mode);
    		
    		throw new LinkException("Can't create new Link: this URL is busy!");
    	}
    	
    	Date startDate = new Date();
    	Date finishDate = JDate.incYear(startDate, 1);
    	
    	JLink link = new JLink(url, target, user, startDate, finishDate);
    	linkService.addLink(link);
    	
    	return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    private boolean isContainSpecialSymbols(String sample, boolean fullControl) {
    	String[] checkers = new String[] 		{" ", ".", ",", "!", "?", "_", "+", "-", "*", "~", "`", "@", "#", "$", "%", "^", "&", "\"", "(", ")", "'", "|", "\\", "/"};
    	String[] lightCheckers = new String[] 	{" ", ",", "`", "'"};
    	
    	String[] work;
    	if(fullControl) {
    		work = checkers;
    	}else {
    		work = lightCheckers;
    	}
    	
    	for (String item : work) {
			if(sample.indexOf(item) != -1) {
				return true;
			}
		}
    	
    	return false;
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
