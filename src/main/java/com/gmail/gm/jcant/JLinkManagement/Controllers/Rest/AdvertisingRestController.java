package com.gmail.gm.jcant.JLinkManagement.Controllers.Rest;

import com.gmail.develop.jcant.JDate;
import com.gmail.gm.jcant.JLinkManagement.DomainRouting.JDomain;
import com.gmail.gm.jcant.JLinkManagement.JPA.Advertising.JAdvertising;
import com.gmail.gm.jcant.JLinkManagement.JPA.Advertising.JAdvertisingException;
import com.gmail.gm.jcant.JLinkManagement.JPA.Advertising.JAdvertisingService;
import com.gmail.gm.jcant.JLinkManagement.JPA.JOperationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class AdvertisingRestController {

    @Autowired
    private JAdvertisingService advertisingService;

    @RequestMapping(value = "/promo/getActual")
    @JDomain(property = "frontend.domains")
    public List<JAdvertising> getAdv(){
        return advertisingService.getInDateAdvertising(new Date());
    }

    @RequestMapping(value = "/promo/{id}")
    @JDomain(property = "frontend.domains")
    public JAdvertising getAdvById(@PathVariable(value = "id") long id) throws JAdvertisingException {

        return advertisingService.getById(id);
    }

    @RequestMapping(value = "/promo/{id}", method = RequestMethod.POST)
    @JDomain(property = "frontend.domains")
    public JOperationInfo<JAdvertising> saveAdv(@PathVariable(value = "id") long id,
                                                @RequestParam String header,
                                                @RequestParam(required = false) String text,
                                                @RequestParam(required = false) String company,
                                                @RequestParam(required = false) String pubStart,
                                                @RequestParam(required = false) String pubFinish
                                                ) throws JAdvertisingException {

        JDate.setDefaultDateFormat("yyyy-MM-dd");

        JAdvertising advertising = null;

        if (id != -1) {
            advertising = advertisingService.getById(id);
        } else {
            advertising = new JAdvertising();
        }

        advertising.setHeader(header);
        if (text != null) advertising.setText(text);
        if (pubStart != null) advertising.setPubStart(JDate.getDate(pubStart));
        if (pubFinish != null) advertising.setPubFinish(JDate.getDate(pubFinish));
        advertising.setCompany(company);

        JDate.setDefaultDateFormat("dd-MM-yyyy");

        advertisingService.save(advertising);

        return new JOperationInfo<JAdvertising>("Advertising update(create) success!", true);
    }

    @RequestMapping(value = "/promo/{id}", method = RequestMethod.DELETE)
    @JDomain(property = "frontend.domains")
    public JOperationInfo<JAdvertising> deleteAdv(@PathVariable(value = "id") long id) throws JAdvertisingException {
        advertisingService.deleteById(id);
        return new JOperationInfo<JAdvertising>("Advertising delete success!", true);
    }
}

