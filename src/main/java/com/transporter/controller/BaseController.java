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
 * @version 1.0
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

	//method to set AccidentReportService for JUnit testing
	public void setServices(AuthenticatedUserService authUserService, AccidentReportService accidentReportService, 
			AccidentCauseService accidentCauseService, CameraService cameraService) {
		this.authUserService = authUserService;
		this.accidentReportService = accidentReportService;
		this.accidentCauseService = accidentCauseService;
		this.cameraService = cameraService;
	}

	@RequestMapping(value = "/", method=RequestMethod.GET)
	public String goMainPage(Map<String, Object> map, HttpSession httpSession){
		List<AccidentReport> currentReports = accidentReportService.getApprovedAccidentReport();
		map.put("currentReports", currentReports);
		return "home";
	}

	//controller redirects the user to view Pending Accident Report page
	@RequestMapping(value = "/accident/pending", method=RequestMethod.GET)
	public String goAccidentReportViewPending(Map<String, Object> map, HttpSession httpSession) {
		if(!authUserService.isAuthenticated(httpSession)) return "redirect:/";
		List<AccidentReport> pendingAccidents = accidentReportService.getPendingAccidentReport();
		map.put("pendingAccidents", pendingAccidents);
		return "accident_view_pending";
	}

	//controller redirects the user to view Resolved Approved Accident Report page
	@RequestMapping(value = "/accident/approved", method=RequestMethod.GET)
	public String goAccidentReportResolveApproved(Map<String, Object> map, HttpSession httpSession) {
		if(!authUserService.isAuthenticated(httpSession)) return "redirect:/";
		List<AccidentReport> approvedAccidents = accidentReportService.getApprovedAccidentReport();
		List<AccidentCause> accidentCauses = accidentCauseService.getAllAccidentCauses();
		map.put("approvedAccidents", approvedAccidents);
		map.put("accidentCauses", accidentCauses);
		return "accident_view_approved";
	}

	//controller redirects the user to view Suggest Camera page
	@RequestMapping(value = "/camera/suggest", method=RequestMethod.GET)
	public String goSuggestCameraPage(Map<String, Object> map, HttpSession httpSession) {
		if(!authUserService.isAuthenticated(httpSession)) return "redirect:/";
		List<Camera> camera = cameraService.getAllCamera();
		map.put("enforcementCamera", camera);
		return "camera_suggest";
	}

	//controller redirects the user to view Manage Camera page
	@RequestMapping(value = "/camera/manage", method=RequestMethod.GET)
	public String goManageCameraPage(Map<String, Object> map, HttpSession httpSession) {
		if(!authUserService.isAuthenticated(httpSession)) return "redirect:/";
		List<Camera> camera = cameraService.getAllCamera();
		map.put("enforcementCamera", camera);
		return "camera_manage";
	}

	//controller logs out the user
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:/";
	}
}
