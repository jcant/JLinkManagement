package com.gmail.gm.jcant.JLinkManagement.Controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import com.gmail.develop.jcant.JDate;
import com.gmail.gm.jcant.JLinkManagement.JPA.JOperationInfo;
import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUserException;

//@ControllerAdvice
//@RestController
public class JAdviceErrorController {

	@ExceptionHandler(Exception.class)
	public JOperationInfo<JUserException> handleError(HttpServletRequest req, Exception ex, Model model) {

		model.addAttribute("datetime", (JDate.getTime(new Date())) + " " + JDate.getDate(new Date()));
		model.addAttribute("url", req.getRequestURI());
		model.addAttribute("message", ex.getMessage());

		return new JOperationInfo<JUserException>(ex.getMessage(), false);
	}

}