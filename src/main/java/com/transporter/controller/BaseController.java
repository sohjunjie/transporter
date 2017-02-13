package com.transporter.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.transporter.service.AuthenticatedUserService;

/**
 * BaseController class handle request for a page
 * and redirect user to the request page
 * @author Soh Jun Jie
 *
 */
@Controller
public class BaseController {

	@Autowired
	private AuthenticatedUserService authUserService;

	@RequestMapping("/")
	public String goMainPage(){
		return "home";
	}
	
	@RequestMapping(value = "/accident/pending", method=RequestMethod.GET)
	public String goAccidentReportViewPending(HttpSession httpSession) {
		if(!authUserService.isAuthenticated(httpSession)) return "redirect:/";
		return "accident_view_pending";
	}
	
	@RequestMapping(value = "/login", method=RequestMethod.POST)
	public @ResponseBody String login(@RequestParam String usernameOrEmail, @RequestParam String password, HttpSession session) {
		boolean success = authUserService.loginUser(usernameOrEmail, password, session);
		if(success){
			return "OK";
		}
		return "";
	}

	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:/";
	}
	
}
