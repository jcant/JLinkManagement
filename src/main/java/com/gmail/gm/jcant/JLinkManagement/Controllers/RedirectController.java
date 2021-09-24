package com.gmail.gm.jcant.JLinkManagement.Controllers;

import com.gmail.gm.jcant.JLinkManagement.DomainRouting.JDomain;
import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLink;
import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLinkException;
import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLinkService;
import com.gmail.gm.jcant.JLinkManagement.JPA.LinkClick.JLinkClickService;

import org.apache.logging.log4j.Logger;
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
    private Logger logger;
	
	@Autowired
    private JLinkService linkService;
    
    @Autowired
    private JLinkClickService linkClickService;

    @JDomain(fromMethod = true)
    @RequestMapping("/")
    public RedirectView index(HttpServletRequest request) throws JLinkException {

        //JLink link = linkService.getByUrlActualEnabled(request.getRequestURL().toString(), new Date());
        JLink link = linkService.getByUrlActualEnabled(getRequestDomain(request), new Date());
        if (link != null) {
        	System.out.println("**** 1)Redirect to link: "+link);
            linkClickService.SaveLinkClick(link, request);
        	//return "redirect:" + link.getTarget();
        	RedirectView rv = new RedirectView(wrapTarget(link.getTarget()), true);
    		rv.setExposeModelAttributes(false);
    		return rv;
        } else {
        	throw new JLinkException("Wrong link requestedwwww! " + getRequestDomain(request));
        }

    }

    @RequestMapping("/{shortcut}")
    public RedirectView indexWithURI(HttpServletRequest request) throws JLinkException {
    	
        //JLink link = linkService.getByUrlActualEnabled(request.getRequestURL().toString(), new Date());
        JLink link = linkService.getByUrlActualEnabled(getRequestDomain(request), new Date());
        if (link != null) {
            System.out.println("**** 2)Redirect to link: "+link);
        	linkClickService.SaveLinkClick(link, request);
        	//return "redirect:" + link.getTarget();
        	RedirectView rv = new RedirectView(wrapTarget(link.getTarget()), true);
    		rv.setExposeModelAttributes(false);
    		return rv;
        } else {
            throw new JLinkException("Wrong link requested@@@! " + getRequestDomain(request));
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
    
    
    private String getRequestDomain(HttpServletRequest request) {

    	String scheme = request.getScheme();
        String name = request.getServerName();
        String uri = request.getRequestURI();
        //String port = "" + request.getServerPort();
        //String url = scheme + "://" + name;
        String url = name;
        
        
        logger.info("********************" + url);
        
        //for now, we exclude server port info:
        //if (!port.equals("")) {
        //    url += ":" + port;
        //}
        
        if (!uri.equals("")) {
        	url += uri;
        }

        return url;
    }
}
