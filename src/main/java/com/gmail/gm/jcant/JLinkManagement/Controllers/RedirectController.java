package com.gmail.gm.jcant.JLinkManagement.Controllers;

import com.gmail.gm.jcant.JLinkManagement.DomainRouting.JDomain;
import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLink;
import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
@JDomain(fromBase = true)
public class RedirectController {
    @Autowired
    private JLinkService linkService;

    @RequestMapping("/")
    public String index(Model model, HttpServletRequest request) {

        JLink link = linkService.getLinkByUrl(request.getRequestURL().toString());
        if (link != null) {
            return "redirect:" + link.getTarget();
        } else {
            return "wrong_url";
        }

    }
}
