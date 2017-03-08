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

/**
 * Handle AJAX request for CRUD operation
 * relating to Camera
 * @author Jun Jie
 * @version 1.0
 */
@Controller
@RequestMapping(value="/camera")
public class CameraController {

	@Autowired
	private CameraService cameraService;
	@Autowired
	private AuthenticatedUserService authUserService;

	@RequestMapping(value = "/suggest/new", method=RequestMethod.POST)
	public @ResponseBody String suggestCamera(
			@RequestParam double lat,
			@RequestParam double lng,
			@RequestParam String cameraLocation,
			@RequestParam int cameraTypeOrdinal, HttpSession httpSession){

		if(!authUserService.isAuthenticated(httpSession)) return "";

		boolean success = cameraService.addNewCamera(lat, lng, cameraLocation, cameraTypeOrdinal);
		if(success){
			return "OK";
		}else{
			return "";
		}

	}

	@RequestMapping(value = "/set/status", method=RequestMethod.POST)
	public @ResponseBody String setCameraStatus(
			@RequestParam int cameraId,
			@RequestParam int cameraStatusOrdinal, HttpSession httpSession){

		if(!authUserService.isAuthenticated(httpSession)) return "";

		boolean success = cameraService.setCameraStatus(cameraId, cameraStatusOrdinal);
		if(success){
			return "OK";
		}else{
			return "";
		}

	}

	@RequestMapping(value = "/set/type", method=RequestMethod.POST)
	public @ResponseBody String setCameraType(
			@RequestParam int cameraId,
			@RequestParam int cameraTypeOrdinal, HttpSession httpSession){

		if(!authUserService.isAuthenticated(httpSession)) return "";

		boolean success = cameraService.setCameraType(cameraId, cameraTypeOrdinal);
		if(success){
			return "OK";
		}else{
			return "";
		}
	}
	

	@RequestMapping(value = "/delete", method=RequestMethod.POST)
	public @ResponseBody String deleteCamera(
			@RequestParam int cameraId, HttpSession httpSession){

		if(!authUserService.isAuthenticated(httpSession)) return "";

		cameraService.delete(cameraId);
		return "OK";
	}
}

