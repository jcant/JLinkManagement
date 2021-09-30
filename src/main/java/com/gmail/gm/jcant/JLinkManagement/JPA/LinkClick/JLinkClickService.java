package com.gmail.gm.jcant.JLinkManagement.JPA.LinkClick;

import javax.servlet.http.HttpServletRequest;

import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLink;
import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLinkException;
import com.gmail.gm.jcant.JLinkManagement.Statistics.JStatistics;

public interface JLinkClickService {

	JStatistics getStatsForLink(JLink link);

	void SaveLinkClick(JLink link, HttpServletRequest request) throws JLinkException;
}
