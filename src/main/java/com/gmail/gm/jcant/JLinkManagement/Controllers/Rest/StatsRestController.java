package com.gmail.gm.jcant.JLinkManagement.Controllers.Rest;

import com.gmail.gm.jcant.JLinkManagement.Helpers.JRoleHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gmail.gm.jcant.JLinkManagement.DomainRouting.JDomain;
import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLink;
import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLinkException;
import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLinkService;
import com.gmail.gm.jcant.JLinkManagement.JPA.LinkClick.JLinkClickService;
import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUserException;
import com.gmail.gm.jcant.JLinkManagement.Statistics.JStatistics;

@RestController
public class StatsRestController {

	@Autowired
    private JLinkService linkService;
	@Autowired
    private JLinkClickService linkClickService;
	
	@RequestMapping(value = "/stats/{linkId}")
	@JDomain(property = "frontend.domains")
	public JStatistics getStats(@PathVariable(value = "linkId") long id) throws JUserException, JLinkException {

		User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		JLink link = linkService.findById(id);

		if ((!JRoleHelper.isHasRole("ROLE_ADMIN", authUser.getAuthorities())) && (!authUser.getUsername().equals(link.getUser().getLogin()))) {
			throw new JUserException("Access deny to get stats for another user");
		}

		return linkClickService.getStatsForLink(link);
	}

}
