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

@Controller
@RequestMapping(value="/accident")
public class AccidentController {

	@Autowired
	private AccidentReportService accidentReportService;
	@Autowired
	private AuthenticatedUserService authUserService;

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

	@RequestMapping(value = "/report/approve", method=RequestMethod.POST)
	public @ResponseBody String approvePendingAccident(@RequestParam int reportId, HttpSession httpSession) {
		if(!authUserService.isAuthenticated(httpSession)) return "";
		AuthenticatedUser authUser = (AuthenticatedUser) httpSession.getAttribute("user");
		boolean success = accidentReportService.approveAccidentReport(authUser, reportId);
		if(!success) return "";
		return "OK";
	}

	@RequestMapping(value = "/report/reject", method=RequestMethod.POST)
	public @ResponseBody String rejectPendingAccident(@RequestParam int reportId, HttpSession httpSession) {
		if(!authUserService.isAuthenticated(httpSession)) return "";
		accidentReportService.delete(reportId);
		return "OK";
	}

	@RequestMapping(value = "/report/resolve", method=RequestMethod.POST)
	public @ResponseBody String resolvePendingAccident(@RequestParam int reportId, HttpSession httpSession) {
		if(!authUserService.isAuthenticated(httpSession)) return "";
		AuthenticatedUser authUser = (AuthenticatedUser) httpSession.getAttribute("user");
		boolean success = accidentReportService.resolveAccidentReport(authUser, reportId);
		if(!success) return "";
		return "OK";
	}

	@RequestMapping(value = "/report/pending/count", method=RequestMethod.GET)
	public @ResponseBody String getPendingAccidentCount(){
		if(accidentReportService.getPendingAccidentCount() > 0)
			return Long.toString(accidentReportService.getPendingAccidentCount());
		return "";
	}

}
