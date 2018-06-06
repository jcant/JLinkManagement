package com.gmail.gm.jcant.JLinkManagement.Controllers;

import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLink;
import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {
    @Autowired
    private JLinkService linkService;

    @RequestMapping("/")
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
        System.out.println("isRootLink url= "+url);
        if (url.endsWith("short2.jca:8080/") || url.endsWith("short3.jca:8080/")) {
            return true;
        } else {
            return false;
        }

    }


}