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
 * Handle user login
 * @author Soh Jun Jie
 * @version 1.0
 */
@Controller
public class LoginController {

	@Autowired
	private AuthenticatedUserService authUserService;
	
	//controller logs in the user and allow the user to accesss the session
	@RequestMapping(value = "/login", method=RequestMethod.POST)
	public @ResponseBody String login(@RequestParam String usernameOrEmail, @RequestParam String password, HttpSession httpSession) {
		boolean success = authUserService.loginUser(usernameOrEmail, password, httpSession);
		if(success){
			return "OK";
		}
		return "";
	}
	
}
