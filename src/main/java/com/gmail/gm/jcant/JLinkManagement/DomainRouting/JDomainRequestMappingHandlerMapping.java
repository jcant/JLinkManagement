package com.gmail.gm.jcant.JLinkManagement.DomainRouting;

import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;


public class JDomainRequestMappingHandlerMapping extends RequestMappingHandlerMapping{
	
	@Autowired
	private Environment environment;
	
	@Autowired
	@Qualifier(value = "JRootLinkDomainListImpl")
	private JDomainList domainList;
	
	@Override
	protected RequestCondition<?> getCustomTypeCondition(Class<?> handlerType) {
		JDomain typeAnnotation = AnnotationUtils.findAnnotation(handlerType, JDomain.class);
		return createCondition(typeAnnotation);
	}

	@Override
	protected RequestCondition<?> getCustomMethodCondition(Method method) {
		JDomain methodAnnotation = AnnotationUtils.findAnnotation(method, JDomain.class);
		return createCondition(methodAnnotation);
	}
	
	private RequestCondition<?> createCondition(JDomain accessMapping) {
		
		//System.out.println("JRootLinkDomainListImpl="+domainList);
		
		JDomainRequestCondition cond = null;
		if (accessMapping != null) {
			if (!accessMapping.property().equals("")) {
				String[] values = environment.getProperty(accessMapping.property()).split(",");
				System.out.println("ann property="+accessMapping.property());
				cond = new JDomainRequestCondition(values);
			}else if(accessMapping.fromBase()) {
				System.out.println("ann fromBase="+accessMapping.fromBase());
				cond = new JDomainRequestCondition(domainList.getDomainList());
			}else{
				System.out.println("ann value="+accessMapping.value());
				cond = new JDomainRequestCondition(accessMapping.value());
			}
		}

		System.out.println("**** createCondition="+cond);
		return cond;
	}
}