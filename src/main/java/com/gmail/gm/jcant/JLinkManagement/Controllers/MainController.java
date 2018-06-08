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
@JDomain(value = {"http://short1.jca:8080/", "http://short2.jca:8080/"})
//@JDomain(property = "frontend.domains")
public class MainController {
    @Autowired
    private JLinkService linkService;

    @RequestMapping("/")
    //@JDomain(value = "http://short1.jca:8080/")
    public String index(Model model, HttpServletRequest request) {
        if (isRootLink(request)) {
            JLink link = linkService.getLinkByUrl(request.getRequestURL().toString());
            if (link != null) {
                return "redirect:" + link.getTarget();
            } else {
                model.addAttribute("target", "*** target ***");
                return "fake_redirect";
            }
        } else {
            return "wrong_url";
        }

    }

    private boolean isRootLink(HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        //System.out.println("isRootLink url= "+url);
        if (url.endsWith("short1.jca:8080/") || url.endsWith("short2.jca:8080/") || url.endsWith("short3.jca:8080/")) {
            return true;
        } else {
            return false;
        }

    }


}