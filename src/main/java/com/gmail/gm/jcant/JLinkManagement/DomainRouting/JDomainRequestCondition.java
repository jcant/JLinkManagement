package com.gmail.gm.jcant.JLinkManagement.DomainRouting;

import java.util.Arrays;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.mvc.condition.RequestCondition;

public class JDomainRequestCondition implements RequestCondition<JDomainRequestCondition> {

    private String[] jDomainValue = null;

    public JDomainRequestCondition(String[] value) {
        jDomainValue = value;
    }

    public JDomainRequestCondition(Collection<String> collection) {
        if (collection != null) {
            jDomainValue = collection.toArray(new String[]{});
        }
    }

    @Override
    public JDomainRequestCondition getMatchingCondition(HttpServletRequest request) {

        System.out.println("IN getMatchingCondition");
        JDomainRequestCondition condition = null;
        String requestDomain = getRequestDomain(request);

        //if (requestDomain != null) {
        for (String domain : jDomainValue) {
            System.out.println("requestDomain=" + requestDomain + " domain=" + domain);
            if (requestDomain.toLowerCase().endsWith(domain.toLowerCase())) {
                condition = this;
                break;
            }
        }
        //}
        System.out.println("***return condition=" + condition);
        return condition;
    }

    @Override
    public JDomainRequestCondition combine(JDomainRequestCondition other) {
        System.out.println("in combine");




        String[] all = new String[this.jDomainValue.length + other.jDomainValue.length];
        System.arraycopy(this.jDomainValue, 0, all, 0, this.jDomainValue.length);
        System.arraycopy(other.jDomainValue, 0, all, this.jDomainValue.length, other.jDomainValue.length);

        return new JDomainRequestCondition(all);
    }

    @Override
    public int compareTo(JDomainRequestCondition other, HttpServletRequest
            request) {
        System.out.println("in compareTo");

        return other.jDomainValue.length - this.jDomainValue.length;
    }

    private String getRequestDomain(HttpServletRequest request) {
        //return request.getRequestURL().toString();
        String scheme = request.getScheme();
        String sname = request.getServerName();
        String sport = "" + request.getServerPort();
        String url = request.getScheme() + "://" + request.getServerName();
        if (!sport.equals("")) {
            url += ":" + sport;
        }
        System.out.println("REQUEST = " + url);
        return url;
    }

}