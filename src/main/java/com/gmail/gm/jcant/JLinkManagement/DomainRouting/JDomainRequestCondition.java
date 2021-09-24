package com.gmail.gm.jcant.JLinkManagement.DomainRouting;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

//import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUserService;

//import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.apache.logging.log4j.Marker;

public class JDomainRequestCondition implements RequestCondition<JDomainRequestCondition> {

	@Autowired
    private Logger logger;

    private String[] jDomainValue = null;

    public JDomainRequestCondition(String[] value) {
        jDomainValue = value;
        logger.info("*************** CREATE!!! INFOOO");
        System.out.println("*************** CREATE!!!");
    }

    public JDomainRequestCondition(Collection<String> collection) {
        if (collection != null) {
            jDomainValue = collection.toArray(new String[]{});
        }
        System.out.println("*************** CREATE2!!!");

    }

    @Override
    public JDomainRequestCondition getMatchingCondition(HttpServletRequest request) {

        JDomainRequestCondition condition = null;
        String requestDomain = getRequestDomain(request);

        for (String domain : jDomainValue) {
            System.out.print("on getMatchingCondition(): request="+requestDomain+" condition="+domain);
        	if (requestDomain.toLowerCase().endsWith(domain.toLowerCase())) {
                System.out.println("   - true");
        		condition = this;
                break;
            }
        	System.out.println("   - false");
        }

        return condition;
    }

    @Override
    public JDomainRequestCondition combine(JDomainRequestCondition other) {

        String[] all = new String[this.jDomainValue.length + other.jDomainValue.length];
        System.arraycopy(this.jDomainValue, 0, all, 0, this.jDomainValue.length);
        System.arraycopy(other.jDomainValue, 0, all, this.jDomainValue.length, other.jDomainValue.length);

        return new JDomainRequestCondition(all);
    }

    @Override
    public int compareTo(JDomainRequestCondition other, HttpServletRequest request) {
        return other.jDomainValue.length - this.jDomainValue.length;
    }

    private String getRequestDomain(HttpServletRequest request) {

        String scheme = request.getScheme();
        String name = request.getServerName();
        //String port = "" + request.getServerPort();
        //String url = scheme + "://" + name;
        String url = name;
        
        logger.info("###############" + url);
        
        //for now, we exclude server port info:
        //if (!port.equals("")) {
        //    url += ":" + port;
        //}

        return url;
    }

}