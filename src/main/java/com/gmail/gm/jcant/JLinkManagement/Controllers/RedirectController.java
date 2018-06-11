package com.gmail.gm.jcant.JLinkManagement.Controllers;

import com.gmail.gm.jcant.JLinkManagement.DomainRouting.JDomain;
import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLink;
import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLinkService;
import com.gmail.gm.jcant.JLinkManagement.Statistics.JStatistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

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
    public String index(Model model, HttpServletRequest request) {

        JLink link = linkService.getLinkByUrl(request.getRequestURL().toString());
        if (link != null) {
            stats.SaveLinkClick(link, request);
        	return "redirect:" + link.getTarget();
        } else {
            return "wrong_url";
        }

    }
}
