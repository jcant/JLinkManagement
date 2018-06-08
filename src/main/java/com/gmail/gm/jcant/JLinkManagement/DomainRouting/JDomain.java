package com.gmail.gm.jcant.JLinkManagement.DomainRouting;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target({TYPE, METHOD})
//@Documented
public @interface JDomain {
	String[] value() default "";
	String property() default "";
	boolean fromBase() default false;
}


/*
 *  Example:
 *  
 *	@RequestMapping("/")
 *	@JDomain(value = {"http://short1.jca:8080/", "http://short2.jca:8080/"})	
 *	
 *	OR
 *
 *	@RequestMapping("/")
 *	@JDomain(property = "frontend.domains")
 */

