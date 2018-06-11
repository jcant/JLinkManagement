package com.gmail.gm.jcant.JLinkManagement.Statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLink;
import com.gmail.gm.jcant.JLinkManagement.JPA.LinkClick.JLinkClick;
import com.gmail.gm.jcant.JLinkManagement.JPA.LinkClick.JLinkClickRepository;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

@Component("SaveLinkClickImpl")
public class SaveLinkClickImpl implements JStatistics {
	
	@Autowired
	private JLinkClickRepository linkClickRepository;
	
	private JLink link;
	
	private HttpServletRequest request;

	public SaveLinkClickImpl() {
	}

	public SaveLinkClickImpl(JLink link, HttpServletRequest request) {
		this.link = link;
		this.request = request;
	}
	

	@Override
	public void SaveLinkClick(JLink link, HttpServletRequest request) {
		if ((link == null)||(request == null)) {
			//throw new exception...
		}
		JLinkClick lc = new JLinkClick(link, new Date(), request.getRemoteAddr());
		linkClickRepository.save(lc);
	}

	public JLink getLink() {
		return link;
	}

	public void setLink(JLink link) {
		this.link = link;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	
	
	
	
	
}
