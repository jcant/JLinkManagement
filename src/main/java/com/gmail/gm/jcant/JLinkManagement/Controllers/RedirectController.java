package com.gmail.gm.jcant.JLinkManagement.Controllers;

import com.gmail.gm.jcant.JLinkManagement.DomainRouting.JDomain;
import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLink;
import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLinkException;
import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLinkService;
import com.gmail.gm.jcant.JLinkManagement.JPA.LinkClick.JLinkClickService;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping("/")
@JDomain(fromMethod = true)
public class RedirectController {
	@Autowired
	private Logger logger;

	@Value("${frontend.domains}")
	private String frontEnd;

	@Autowired
	private JLinkService linkService;

	@Autowired
	private JLinkClickService linkClickService;

	@RequestMapping("/")
	public RedirectView index(HttpServletRequest request) throws JLinkException {
		String url;
		JLink link = linkService.getByUrlActualEnabled(getRequestDomain(request), new Date());
		if (link != null) {
			linkClickService.SaveLinkClick(link, request);
			url = wrapTarget(link.getTarget());
			logger.info("Sub-domain redirection: " + link);
		} else {
			logger.warn("Wrong link requested! " + getRequestDomain(request));
			url = wrapTarget(frontEnd);
		}

		RedirectView rv = new RedirectView(url, true);
		rv.setExposeModelAttributes(false);
		return rv;
	}

	@RequestMapping("/{shortcut}")
	public RedirectView indexWithURI(HttpServletRequest request) throws JLinkException {
		String url;
		JLink link = linkService.getByUrlActualEnabled(getRequestDomain(request), new Date());
		if (link != null) {
			linkClickService.SaveLinkClick(link, request);
			url = wrapTarget(link.getTarget());
			logger.info("Uri-link redirection: " + link);
		} else {
			logger.warn("Wrong link requested! " + getRequestDomain(request));
			url = wrapTarget(frontEnd);
		}

		RedirectView rv = new RedirectView(url, true);
		rv.setExposeModelAttributes(false);
		return rv;
	}

	private String wrapTarget(String target) {
		String result = target.toLowerCase();
		if (result.startsWith("http://") || result.startsWith("https://")) {
			return result;
		} else {
			return "http://" + result;
		}
	}

	private String getRequestDomain(HttpServletRequest request) {

		String name = request.getServerName();
		String uri = request.getRequestURI();

		String url = name;

		if (!uri.equals("") && !uri.equals("/")) {
			url += uri;
		}

		return url;
	}
}
