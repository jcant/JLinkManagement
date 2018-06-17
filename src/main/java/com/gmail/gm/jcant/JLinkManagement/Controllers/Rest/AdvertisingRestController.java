package com.gmail.gm.jcant.JLinkManagement.Controllers.Rest;

import com.gmail.gm.jcant.JLinkManagement.DomainRouting.JDomain;
import com.gmail.gm.jcant.JLinkManagement.JPA.Advertising.JAdvertising;
import com.gmail.gm.jcant.JLinkManagement.JPA.Advertising.JAdvertisingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class AdvertisingRestController {

    @Autowired
    private JAdvertisingService advertisingService;

    @RequestMapping(value = "/promo/getActual")
    @JDomain(property = "frontend.domains")
    public List<JAdvertising> getAdv(){
        //System.out.println("sending adv...");
        return advertisingService.getInDateAdvertising(new Date());
    }
}
