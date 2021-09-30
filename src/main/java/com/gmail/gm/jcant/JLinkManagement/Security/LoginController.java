package com.gmail.gm.jcant.JLinkManagement.Security;

import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUser;
import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUserException;
import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUserService;
import com.gmail.gm.jcant.JLinkManagement.JPA.UserLog.JUserLogException;
import com.gmail.gm.jcant.JLinkManagement.JPA.UserLog.JUserLogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/loginsuccess")
public class LoginController {
	@Autowired
	private JUserLogService userLogService;
	@Autowired
	private JUserService userService;

	@RequestMapping
	public String loginPage(Model model, HttpServletRequest request) throws JUserException, JUserLogException {

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		JUser dbUser = userService.getUserByLogin(user.getUsername());

		if (dbUser.isBlocked()) {
			SecurityContextHolder.clearContext();
			throw new JUserException("Your User (login = " + dbUser.getLogin() + ") is BLOCKED!");
		}

		if (dbUser.isResetPassword()) {
			return "redirect:/profile";
		}

		userLogService.SaveUserComeIn(dbUser, request);
		return "redirect:/";
	}
}
