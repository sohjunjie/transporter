package com.transporter.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.transporter.service.AuthenticatedUserService;
import com.transporter.service.CameraService;

@Controller
@RequestMapping(value="/camera")
public class CameraController {

	@Autowired
	private CameraService cameraService;
	@Autowired
	private AuthenticatedUserService authUserService;

	@RequestMapping(value = "/suggest/new", method=RequestMethod.POST)
	public @ResponseBody String reportAccident(
			@RequestParam double lat,
			@RequestParam double lng,
			@RequestParam String cameraLocation,
			@RequestParam int cameraTypeOrdinal, HttpSession httpSession){

		if(!authUserService.isAuthenticated(httpSession)) return "";
		
		boolean success = cameraService.suggestNewCamera(lat, lng, cameraLocation, cameraTypeOrdinal);
		if(success){
			return "OK";
		}else{
			return "";
		}
	}
	
}
