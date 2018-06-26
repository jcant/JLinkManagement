package com.gmail.gm.jcant.JLinkManagement.Controllers;

import com.gmail.gm.jcant.JLinkManagement.DomainRouting.JDomain;
import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLink;
import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLinkException;
import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLinkService;
import com.gmail.gm.jcant.JLinkManagement.JPA.LinkClick.JLinkClickService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping("/")
@JDomain(fromMethod = true)
public class RedirectController {
    @Autowired
    private JLinkService linkService;
    
    @Autowired
    private JLinkClickService linkClickService;

    @RequestMapping("/")
    public RedirectView index(HttpServletRequest request) throws JLinkException {

        JLink link = linkService.getByUrlActualEnabled(request.getRequestURL().toString(), new Date());
        if (link != null) {
            linkClickService.SaveLinkClick(link, request);
        	//return "redirect:" + link.getTarget();
        	RedirectView rv = new RedirectView(wrapTarget(link.getTarget()), true);
    		rv.setExposeModelAttributes(false);
    		return rv;
        } else {
        	throw new JLinkException("Wrong link requested! " + request.getRequestURL().toString());
        }

    }

    @RequestMapping("/{shortcut}")
    public RedirectView indexWithURI(HttpServletRequest request) throws JLinkException {
    	
        JLink link = linkService.getByUrlActualEnabled(request.getRequestURL().toString(), new Date());
        if (link != null) {
            linkClickService.SaveLinkClick(link, request);
        	//return "redirect:" + link.getTarget();
        	RedirectView rv = new RedirectView(wrapTarget(link.getTarget()), true);
    		rv.setExposeModelAttributes(false);
    		return rv;
        } else {
            throw new JLinkException("Wrong link requested! " + request.getRequestURL().toString());
        }
    }
    
    private String wrapTarget(String target) {
    	String result = target.toLowerCase();
    	if(result.startsWith("http://")||result.startsWith("https://")) {
    		return result;
    	}else {
    		return "http://"+result;
    	}
    }
}
