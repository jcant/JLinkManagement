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
		return createCondition(typeAnnotation);
	}

	@Override
	protected RequestCondition<?> getCustomMethodCondition(Method method) {
		System.out.println("in getCustomMethodCondition");
		JDomain methodAnnotation = AnnotationUtils.findAnnotation(method, JDomain.class);
		return createCondition(methodAnnotation);
	}
	
	private RequestCondition<?> createCondition(JDomain accessMapping) {
		System.out.println("in createCondition="+accessMapping);
		JDomainRequestCondition cond = null;
		if (accessMapping != null) {
			if (!accessMapping.property().equals("")) {
				cond = new JDomainRequestCondition(accessMapping.property());
			}else{
				cond = new JDomainRequestCondition(accessMapping.value());
			}
		}
		return cond;
	}
}