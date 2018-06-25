package com.gmail.gm.jcant.JLinkManagement.Controllers;

import com.gmail.gm.jcant.JLinkManagement.DomainRouting.JDomain;
import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLink;
import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLinkException;
import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLinkService;
import com.gmail.gm.jcant.JLinkManagement.JPA.LinkClick.JLinkClickService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
    
//    @Autowired
//	@Qualifier(value = "SaveLinkClickImpl")
//	private JStatistics stats;

    @RequestMapping("/")
    public String index(/*Model model,*/ HttpServletRequest request) throws JLinkException {

        JLink link = linkService.getByUrlActualEnabled(request.getRequestURL().toString(), new Date());
        if (link != null) {
            //stats.SaveLinkClick(link, request);
            linkClickService.SaveLinkClick(link, request);
        	return "redirect:" + link.getTarget();
        } else {
            //return "wrong_url";
        	throw new JLinkException("Wrong link requested! " + request.getRequestURL().toString());
        }

    }

    @RequestMapping("/{shortcut}")
    public String indexWithURI(/*Model model,*/ HttpServletRequest request) throws JLinkException {
    	
        JLink link = linkService.getByUrlActualEnabled(request.getRequestURL().toString(), new Date());
        if (link != null) {
            //stats.SaveLinkClick(link, request);
            linkClickService.SaveLinkClick(link, request);
        	return "redirect:" + link.getTarget();
        } else {
            //return "wrong_url";
            throw new JLinkException("Wrong link requested! " + request.getRequestURL().toString());
        }
    }
}
