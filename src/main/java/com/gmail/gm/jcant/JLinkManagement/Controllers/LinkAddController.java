package com.gmail.gm.jcant.JLinkManagement.Controllers;

import java.security.Principal;
import java.util.Date;

import javax.naming.LinkException;

import com.gmail.gm.jcant.JLinkManagement.Helpers.JModelHelper;
import com.gmail.gm.jcant.JLinkManagement.Helpers.JSymbolsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.view.RedirectView;

import com.gmail.gm.jcant.JDate;
import com.gmail.gm.jcant.JLinkManagement.DomainRouting.JDomain;
import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLink;
import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLinkService;
import com.gmail.gm.jcant.JLinkManagement.JPA.RootLink.JRootLink;
import com.gmail.gm.jcant.JLinkManagement.JPA.RootLink.JRootLinkService;
import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUserException;
import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUserService;


@Controller
@RequestMapping(value = "/")
@JDomain(property = "frontend.domains")
@SessionAttributes("jlink")
public class LinkAddController {

	@Autowired
	private JUserService userService;
	@Autowired
	private JLinkService linkService;
	@Autowired
	private JRootLinkService rootLinkService;
	
	@ModelAttribute("jlink")
    public JLink getVisitor () {
        JLink link = new JLink();
		return link;
    }
	
    @RequestMapping(value = "/payment", method = RequestMethod.POST)
    public String payment(Model model, Principal principal, 
    		@RequestParam String rootLinks, @RequestParam String checkURL, 
    		@RequestParam String linkMode, @RequestParam String target, 
    		@ModelAttribute("jlink") JLink link) throws JUserException, LinkException {
    	
    	JModelHelper.prepareModel(model, principal, "payment");
    	
    	JRootLink rootLink = rootLinkService.getRootLinkByUrl(rootLinks);
		if (rootLink == null) {
			throw new LinkException("Can't create new Link: base URL is invalid!");
		}
		if (JSymbolsHelper.isContainSpecialSymbols(target, false)) {
			throw new LinkException("Can't create new Link: selected parameters contain special symbols!");
		}
		
    	
    	link.setTarget(target);
    	link.setUser(userService.getUserByLogin(principal.getName()));
    	link.setStartDate(new Date());
    	
    	if (linkMode.equals("subdomain")) {
			link.setUrl("http://" + checkURL + "." + rootLinks + "/");
		}
		if (linkMode.equals("parameter")) {
			link.setUrl("http://" + rootLinks + "/" + checkURL);
		}
		
		if (!linkService.isFreeByUrl(link.getUrl())) {
			throw new LinkException("Can't create new Link: this URL is busy!");
		}
		
		link.setFree(false);
		link.setEnabled(true);
		link.setFinishDate(JDate.incYear(link.getStartDate(), 1));
    	
        return "payment";
    }
    
    
    @RequestMapping(value = "/paymentconfirm", method = RequestMethod.POST)
    public RedirectView paymentconfirm(Model model, Principal principal, @RequestParam String confirm, @ModelAttribute("jlink") JLink link, SessionStatus sessionStatus) throws JUserException {
    	
    	JModelHelper.prepareModel(model, principal, "payment");
    	
    	model.addAttribute("addLink", link);
    	
    	if (confirm.equals("confirm")) {
    		linkService.addLink(link);
    		model.addAttribute("success", true);
    	} else {
    		model.addAttribute("success", false);
    	}
    		
    	sessionStatus.setComplete();
        
    	//return "links";
    	RedirectView rv = new RedirectView("/links", true);
		rv.setExposeModelAttributes(false);
		return rv;
    }
    
}
