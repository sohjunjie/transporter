package com.transporter.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.transporter.model.user.AuthenticatedUser;
import com.transporter.service.AccidentReportService;
import com.transporter.service.AuthenticatedUserService;

/**
 * Handle AJAX request for CRUD operation
 * relating to accident
 * @author Soh Jun Jie
 * @version 1.0
 */
@Controller
@RequestMapping(value="/accident")
public class AccidentController {

	@Autowired
	private AccidentReportService accidentReportService;
	@Autowired
	private AuthenticatedUserService authUserService;

	//report new accident with accident details like latitude, longitude, date&time, description and image
	@RequestMapping(value = "/report/new", method=RequestMethod.POST)
	public @ResponseBody String reportAccident(
			@RequestParam String accidentLocation,
			@RequestParam double lat,
			@RequestParam double lng,
			@RequestParam @DateTimeFormat(pattern="dd/MM/yyyy HH:mm") Date accidentDateTime,
			@RequestParam String accidentDescription,
			@RequestParam MultipartFile accidentImage) {
		boolean success = accidentReportService.add(accidentLocation, lat, lng, accidentDateTime, accidentDescription, accidentImage);
		if(success){
			return "OK";
		}else{
			return "";
		}
	}

	//authenticated user can mark reported accident as approved
	@RequestMapping(value = "/report/approve", method=RequestMethod.POST)
	public @ResponseBody String approvePendingAccident(@RequestParam int reportId, HttpSession httpSession) {
		if(!authUserService.isAuthenticated(httpSession)) return "";
		AuthenticatedUser authUser = (AuthenticatedUser) httpSession.getAttribute("user");
		boolean success = accidentReportService.approveAccidentReport(authUser, reportId);
		if(!success) return "";
		return "OK";
	}
	
	//authenticated user can mark reported accident as rejected
	@RequestMapping(value = "/report/reject", method=RequestMethod.POST)
	public @ResponseBody String rejectPendingAccident(@RequestParam int reportId, HttpSession httpSession) {
		if(!authUserService.isAuthenticated(httpSession)) return "";
		accidentReportService.delete(reportId);
		return "OK";
	}

	//authenticated user can mark approved accident as resolved
	@RequestMapping(value = "/report/resolve", method=RequestMethod.POST)
	public @ResponseBody String resolveApprovedAccident(@RequestParam int reportId, @RequestParam int causeId, @RequestParam int numOfCasualties, HttpSession httpSession) {
		if(!authUserService.isAuthenticated(httpSession)) return "";
		AuthenticatedUser authUser = (AuthenticatedUser) httpSession.getAttribute("user");
		boolean success = accidentReportService.resolveAccidentReport(authUser, reportId, causeId, numOfCasualties);
		if(!success) return "";
		return "OK";
	}

	//controller can show the number of current pending reports
	@RequestMapping(value = "/report/pending/count", method=RequestMethod.GET)
	public @ResponseBody String getPendingAccidentCount(){
		Long pendingAccidentCount = accidentReportService.getPendingAccidentCount();
		if(pendingAccidentCount > 0)
			return Long.toString(pendingAccidentCount);
		return "";
	}
	
	//controller can show the number of approved reports that are yet to be resolved
	@RequestMapping(value = "/report/approved/count", method=RequestMethod.GET)
	public @ResponseBody String getApprovedAccidentCount(){
		Long approvedAccidentCount = accidentReportService.getApprovedAccidentCount();
		if(approvedAccidentCount > 0)
			return Long.toString(approvedAccidentCount);
		return "";
	}
	
}
