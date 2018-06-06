package com.gmail.gm.jcant.JLinkManagement.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class RequestFilter implements Filter {

    @Autowired
    DispatcherServlet dispatcher;

    public void init(FilterConfig filterConfig) {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

        System.out.println("IN FILTER!!!");



        if (isRootLink((HttpServletRequest) request)) {
            System.out.println("Filter set RootLink");
            dispatcher.getHandlerMappings().get(0).getHandler(request).toString();
            request.setAttribute("RootLink", "true");
        } else {
            System.out.println("Filter set FrontEnd");
            request.setAttribute("FrontEnd", "true");
        }

        filterChain.doFilter(request, response);

    }

    public void destroy() {
    }

    private boolean isRootLink(HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        System.out.println("isRootLink url= " + url);
        if (url.endsWith("short2.jca:8080/") || url.endsWith("short3.jca:8080/")) {
            return true;
        } else {
            return false;
        }

    }
}