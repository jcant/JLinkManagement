package com.gmail.gm.jcant.JLinkManagement.Controllers;

import com.gmail.gm.jcant.JLinkManagement.DomainRouting.JDomain;
import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLink;
import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLinkService;
import com.gmail.gm.jcant.JLinkManagement.Statistics.JStatistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
	@Qualifier(value = "SaveLinkClickImpl")
	private JStatistics stats;

    @RequestMapping("/")
    public String index(/*Model model,*/ HttpServletRequest request) {

        //JLink link = linkService.findByUrl(request.getRequestURL().toString());
        JLink link = linkService.getByUrlActualEnabled(request.getRequestURL().toString(), new Date());
        if (link != null) {
            stats.SaveLinkClick(link, request);
        	return "redirect:" + link.getTarget();
        } else {
            return "wrong_url";
        }

    }

    @RequestMapping("/{shortcut}")
    //public String indexWithURI(Model model, @PathVariable(value = "shortcut") String shortcut){
    public String indexWithURI(/*Model model,*/ HttpServletRequest request){
    	
    	//JLink link = linkService.findByUrl(request.getRequestURL().toString());
        JLink link = linkService.getByUrlActualEnabled(request.getRequestURL().toString(), new Date());
        if (link != null) {
            stats.SaveLinkClick(link, request);
        	return "redirect:" + link.getTarget();
        } else {
            return "wrong_url";
        }
    }
}
