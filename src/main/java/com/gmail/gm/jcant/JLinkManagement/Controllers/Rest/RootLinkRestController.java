package com.gmail.gm.jcant.JLinkManagement.Controllers.Rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gmail.gm.jcant.JLinkManagement.DomainRouting.JDomain;
import com.gmail.gm.jcant.JLinkManagement.JPA.RootLink.JRootLink;
import com.gmail.gm.jcant.JLinkManagement.JPA.RootLink.JRootLinkService;

@RestController
public class RootLinkRestController {

    @Autowired
    private JRootLinkService rootLinkService;

    @RequestMapping(value = "/rootlinks/getActual")
    @JDomain(property = "frontend.domains")
    public List<JRootLink> getRootLinks() {

    	return rootLinkService.getAllRootLinks();
    }
}