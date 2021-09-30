package com.gmail.gm.jcant.JLinkManagement.Controllers.Rest;

import java.util.List;

import com.gmail.gm.jcant.JLinkManagement.JPA.JOperationInfo;
import com.gmail.gm.jcant.JLinkManagement.JPA.RootLink.JRootLinkException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.gmail.gm.jcant.JLinkManagement.DomainRouting.JDomain;
import com.gmail.gm.jcant.JLinkManagement.JPA.RootLink.JRootLink;
import com.gmail.gm.jcant.JLinkManagement.JPA.RootLink.JRootLinkService;

@RestController
public class RootLinkRestController {

    @Autowired
    private JRootLinkService rootLinkService;

    @RequestMapping(value = "/rootlinks/getActual")
    @JDomain(property = "frontend.domains")
    public List<JRootLink> getActual() {

    	return rootLinkService.getEnabledRootLinks();
    }

    @RequestMapping(value = "/rootlinks/all")
    @JDomain(property = "frontend.domains")
    public List<JRootLink> getAll() {

        return rootLinkService.getAllRootLinks();
    }

    @RequestMapping(value = "/rootlinks/{id}", method = RequestMethod.POST)
    @JDomain(property = "frontend.domains")
    public JOperationInfo<JRootLink> saveRootLink(@PathVariable(value = "id") long id,
                                                 @RequestParam String url,
                                                 @RequestParam boolean enabled
                                                ) throws JRootLinkException {

    	throw new JRootLinkException("Sorry! It's a test server, Root link operations are disabled!");
    	
//        JRootLink rootLink = null;
//
//        if (id != -1) {
//            rootLink = rootLinkService.getById(id);
//        } else {
//            rootLink = new JRootLink();
//        }
//
//        rootLink.setUrl(url);
//        rootLink.setEnabled(enabled);
//
//        rootLinkService.save(rootLink);
//
//        return new JOperationInfo<JRootLink>("RootLink update(create) success!", true);
    }

    @RequestMapping(value = "/rootlinks/{id}", method = RequestMethod.DELETE)
    @JDomain(property = "frontend.domains")
    public JOperationInfo<JRootLink> deleteRootLink(@PathVariable(value = "id") long id) throws JRootLinkException {
    	
    	throw new JRootLinkException("Sorry! It's a test server, Root link operations are disabled!");
    	
//        rootLinkService.deleteById(id);
//        return new JOperationInfo<JRootLink>("RootLink delete success!", true);
    }
}