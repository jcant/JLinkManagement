package com.gmail.gm.jcant.JLinkManagement.DomainRouting;

import java.lang.reflect.Method;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;


public class JDomainRequestMappingHandlerMapping extends RequestMappingHandlerMapping{
	@Override 
	protected RequestCondition<?> getCustomTypeCondition(Class<?> handlerType) {
        
		System.out.println("in getCustomTypeCondition");
		
		JDomain typeAnnotation = AnnotationUtils.findAnnotation(handlerType, JDomain.class);
//        JDomainRequestCondition requestCondition = null;
//        if (typeAnnotation != null) {
//        	requestCondition = new JDomainRequestCondition(typeAnnotation.value());
//        }
//        
//        return requestCondition;
		return createCondition(typeAnnotation);
    }
	
	@Override
	protected RequestCondition<?> getCustomMethodCondition(Method method) {
		JDomain methodAnnotation = AnnotationUtils.findAnnotation(method, JDomain.class);
		return createCondition(methodAnnotation);
	}
	
	private RequestCondition<?> createCondition(JDomain accessMapping) {
		return (accessMapping != null) ? new JDomainRequestCondition(accessMapping.value()) : null;
	}
}