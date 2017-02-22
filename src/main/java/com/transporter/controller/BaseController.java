package com.transporter.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.transporter.model.AccidentCause;
import com.transporter.model.AccidentReport;
import com.transporter.model.Camera;
import com.transporter.service.AccidentCauseService;
import com.transporter.service.AccidentReportService;
import com.transporter.service.AuthenticatedUserService;
import com.transporter.service.CameraService;

/**
 * BaseController class handle request for a page
 * and redirect user to the requested page
 * @author Soh Jun Jie
 */
@Controller
public class BaseController {

	@Autowired
	private AuthenticatedUserService authUserService;
	@Autowired
	private AccidentReportService accidentReportService;
	@Autowired
	private AccidentCauseService accidentCauseService;
	@Autowired
	private CameraService cameraService;	

	@RequestMapping("/")
	public String goMainPage(){
		return "home";
	}

	@RequestMapping(value = "/accident/pending", method=RequestMethod.GET)
	public String goAccidentReportViewPending(Map<String, Object> map, HttpSession httpSession) {
		if(!authUserService.isAuthenticated(httpSession)) return "redirect:/";
		List<AccidentReport> pendingAccidents = accidentReportService.getPendingAccidentReport();
		map.put("pendingAccidents", pendingAccidents);
		return "accident_view_pending";
	}

	@RequestMapping(value = "/accident/approved", method=RequestMethod.GET)
	public String goAccidentReportResolveApproved(Map<String, Object> map, HttpSession httpSession) {
		if(!authUserService.isAuthenticated(httpSession)) return "redirect:/";
		List<AccidentReport> approvedAccidents = accidentReportService.getApprovedAccidentReport();
		List<AccidentCause> accidentCauses = accidentCauseService.getAllAccidentCauses();
		map.put("approvedAccidents", approvedAccidents);
		map.put("accidentCauses", accidentCauses);
		return "accident_view_approved";
	}

	@RequestMapping(value = "/camera/suggest", method=RequestMethod.GET)
	public String goSuggestCameraPage(Map<String, Object> map, HttpSession httpSession) {
		if(!authUserService.isAuthenticated(httpSession)) return "redirect:/";
		List<Camera> speedCameras = cameraService.getAllSpeedCamera();
		List<Camera> trafficCameras = cameraService.getAllTrafficCamera();
		map.put("speedCameras", speedCameras);
		map.put("trafficCameras", trafficCameras);
		return "camera_suggest";
	}

	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:/";
	}
	
}
