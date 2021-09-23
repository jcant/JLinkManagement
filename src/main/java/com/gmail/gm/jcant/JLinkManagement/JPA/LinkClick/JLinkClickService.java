package com.gmail.gm.jcant.JLinkManagement.JPA.LinkClick;

import javax.servlet.http.HttpServletRequest;

import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLink;
import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLinkException;
import com.gmail.gm.jcant.JLinkManagement.Statistics.JStatistics;

public interface JLinkClickService {
	//List<JLinkClick> getByUrl(String url);
    //boolean existsByUrl(String url);
    //void addLinkClick(JLinkClick linkClick);
    //void updateLinkClick(JLinkClick linkClick);
    //List<JLinkClick> getByUser(JUser user);
    
	JStatistics getStatsForLink(JLink link);
	
    void SaveLinkClick(JLink link, HttpServletRequest request) throws JLinkException;
}
