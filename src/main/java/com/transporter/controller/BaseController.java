package com.transporter.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.transporter.model.AccidentCause;
import com.transporter.model.AccidentReport;
import com.transporter.service.AccidentCauseService;
import com.transporter.service.AccidentReportService;
import com.transporter.service.AuthenticatedUserService;
import com.transporter.service.SummaryReportService;

/**
 * BaseController class handle request for a page
 * and redirect user to the request page
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
	private SummaryReportService summaryReportService;

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
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping(value="/summary/cause", method=RequestMethod.GET)
	public String goSummaryCause(Map<String, Object> map, HttpSession httpSession) {
		//List<AccidentReport> accidentReports = accidentReportService.getAllAccidentReport();
		List<AccidentCause> accidentCauses = accidentCauseService.getAllAccidentCauses();
		/*int[] causeCount = summaryReportService.summariseByCause (accidentReports, accidentCauses);
		map.put("accidentCauses", accidentCauses);
		map.put("causeCount", causeCount);*/
		return "summary_cause";
	}
	/*
	@RequestMapping(value="/summary/time", method=RequestMethod.GET)
	public String goSummaryTime(Map<String, Object> map, HttpSession httpSession) {
		List<AccidentReport> accidentReports = accidentReportService.getAllAccidentReport();
		int[] hrAccidentCount = summaryReportService.summariseByTime(accidentReports);
		map.put("hrAccidentCount", hrAccidentCount);
		return "summary_time";
	}*/
	
}
