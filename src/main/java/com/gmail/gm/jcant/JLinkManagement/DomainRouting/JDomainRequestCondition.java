package com.gmail.gm.jcant.JLinkManagement.DomainRouting;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

public class JDomainRequestCondition implements RequestCondition<JDomainRequestCondition> {

	//@Value("${frontend.domains}")
	private final Set<String> jdomains;

	public JDomainRequestCondition(String[] value) {
		System.out.println("in JDomainRequesCondition constructor " + Arrays.toString(value));
		jdomains = Collections.unmodifiableSet(new HashSet<String>(Arrays.asList(value)));
	}
	
	public JDomainRequestCondition(Collection<String> value) {
		jdomains = Collections.unmodifiableSet(new HashSet<String>(value));
	}
	
	@Override
	public JDomainRequestCondition getMatchingCondition(HttpServletRequest request) {
		
		System.out.println("in getMatchingCondition");
		
		JDomainRequestCondition condition = null;

		try {
			String value = getRequestDomain(request); // this is where you will have the code to work out the subdomain
			if (value != null) {
				for (String s : this.jdomains) {
					System.out.println("jdomain="+s+" value="+value);
					if (s.equalsIgnoreCase(value)) {
						condition = this;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return condition;
	}

	
	@Override
	public JDomainRequestCondition combine(JDomainRequestCondition other) {
		System.out.println("in combine");
		
		Set<String> allRoles = new LinkedHashSet<String>(this.jdomains);
		allRoles.addAll(other.jdomains);
		return new JDomainRequestCondition(allRoles);
	}

	@Override
	public int compareTo(JDomainRequestCondition other, HttpServletRequest request) {
		System.out.println("in compareTo");
		
		return other.jdomains.size() - this.jdomains.size();
	}

	
	
	
	private String getRequestDomain(HttpServletRequest request) {
		System.out.println("in getRequestDomain");
		String url = request.getRequestURL().toString();
		return url;
	}
}