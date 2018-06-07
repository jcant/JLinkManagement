package com.gmail.gm.jcant.JLinkManagement.DomainRouting;

import org.springframework.core.env.Environment;

public class EnvironmentGetter {
    private Environment environment;

    public EnvironmentGetter(Environment environment){
        this.environment = environment;
    }

    public String getProperty(String property){
        return environment.getProperty(property);
    }
}
