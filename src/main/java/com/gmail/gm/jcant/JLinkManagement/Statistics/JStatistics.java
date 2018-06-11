package com.gmail.gm.jcant.JLinkManagement.Statistics;

import javax.servlet.http.HttpServletRequest;

import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLink;

public interface JStatistics {
	void SaveLinkClick(JLink link, HttpServletRequest request);
}
