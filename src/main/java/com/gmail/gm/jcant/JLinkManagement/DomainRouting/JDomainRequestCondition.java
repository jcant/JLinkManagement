package com.gmail.gm.jcant.JLinkManagement.DomainRouting;

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
			jDomainValue = collection.toArray(new String[] {});
		}
	}

	@Override
	public JDomainRequestCondition getMatchingCondition(HttpServletRequest request) {

		JDomainRequestCondition condition = null;
		String requestDomain = getRequestDomain(request);

		if (requestDomain != null) {
			for (String domain : jDomainValue) {
				System.out.println("requestDomain="+requestDomain+" domain="+domain);
				if (domain.equalsIgnoreCase(requestDomain)) {
					condition = this;
					break;
				}
			}
		}

		return condition;
	}

	// @Override
	// public JDomainRequestCondition combine(JDomainRequestCondition other) {
	// System.out.println("in combine");
	//
	// Set<String> allRoles = new LinkedHashSet<String>(this.jdomains);
	// allRoles.addAll(other.jdomains);
	// return new JDomainRequestCondition(allRoles);
	// }
	//
	// @Override
	// public int compareTo(JDomainRequestCondition other, HttpServletRequest
	// request) {
	// System.out.println("in compareTo");
	//
	// return other.jdomains.size() - this.jdomains.size();
	// }

	@Override
	public JDomainRequestCondition combine(JDomainRequestCondition arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int compareTo(JDomainRequestCondition arg0, HttpServletRequest arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

	private String getRequestDomain(HttpServletRequest request) {
		//return request.getRequestURL().toString();
		return request.getQueryString();
	}

}