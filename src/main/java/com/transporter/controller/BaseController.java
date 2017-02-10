package com.transporter.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.transporter.service.AuthenticatedUserService;

@Controller
public class BaseController {

	@Autowired
	private AuthenticatedUserService authUserService;

	@RequestMapping("/")
	public String goMainPage(){
		return "home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody String login(@RequestParam String usernameOrEmail, @RequestParam String password, HttpSession session) {
		boolean success = authUserService.loginUser(usernameOrEmail, password, session);
		if(success){
			return "OK";
		}
		return "";
	}

	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(Map<String, Object> map, HttpSession session){

		session.invalidate();
		return "redirect:/";

	}
	
}
