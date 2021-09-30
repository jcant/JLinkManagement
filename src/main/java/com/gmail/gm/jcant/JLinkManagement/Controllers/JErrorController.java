package com.gmail.gm.jcant.JLinkManagement.Controllers;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gmail.develop.jcant.JDate;

//@Controller
public class JErrorController implements ErrorController{

	
	@RequestMapping("/error")
    public String error(HttpServletRequest req, Model model) {

		model.addAttribute("datetime", (JDate.getTime(new Date())) + " " +JDate.getDate(new Date()));
		model.addAttribute("url", req.getRequestURI());
		model.addAttribute("message", "An error has occurred");

	    return "jerror";
    }
	
	@RequestMapping("/unauthorized")
    public String unauthorized(HttpServletRequest req, Model model) {

		model.addAttribute("datetime", (JDate.getTime(new Date())) + " " +JDate.getDate(new Date()));
		model.addAttribute("url", req.getRequestURI());
		model.addAttribute("message", "Access denied!");

	    return "jerror";
    }
	
}
