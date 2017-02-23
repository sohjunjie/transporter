package com.transporter.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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
	/*
	@RequestMapping(value="/summary/cause", method=RequestMethod.GET)
	public String goSummaryCause(Map<String, Object> map, HttpSession httpSession) {
		List<AccidentReport> accidentReports = accidentReportService.getAllAccidentReport();
		List<AccidentCause> accidentCauses = accidentCauseService.getAllAccidentCauses();
		int[] causeCount = summaryReportService.summariseByCause (accidentReports, accidentCauses);
		map.put("accidentCauses", accidentCauses);
		map.put("causeCount", causeCount);
		return "summary_cause";
	}*/
	
	@RequestMapping(value="/summary/cause", method=RequestMethod.GET)
	public String goSummaryCauseWithDate(@RequestParam(value="startdate", required=false) Date startDate, @RequestParam(value="enddate", required=false) Date endDate, Map<String, Object> map) {
		List<AccidentCause> accidentCauses = accidentCauseService.getAllAccidentCauses();
		List<AccidentReport> accidentReports;
		if (startDate==null || endDate ==null)
			accidentReports = accidentReportService.getAllAccidentReport();
		else
			accidentReports = accidentReportService.getAccidentReportBetweenDate(startDate, endDate);
		int[] causeCount = summaryReportService.summariseByCause (accidentReports, accidentCauses);
		map.put("accidentCauses", accidentCauses);
		map.put("causeCount", causeCount);
		return "summary_cause";
	   }
	
	
	@RequestMapping(value="/summary/time", method=RequestMethod.GET)
	public String goSummaryTimeWithDate(@RequestParam(value="startdate", required=false) Date startDate, @RequestParam(value="enddate", required=false) Date endDate, Map<String, Object> map, HttpSession httpSession) {
		List<AccidentReport> accidentReports;
		if (startDate==null || endDate ==null)
			accidentReports = accidentReportService.getAllAccidentReport();
		else
			accidentReports = accidentReportService.getAccidentReportBetweenDate(startDate, endDate);
		int[] hrAccidentCount = summaryReportService.summariseByTime(accidentReports);
		int[] hrsOfDay = new int[24];
		for (int i = 0; i < 24; i++) {
			hrsOfDay[i] = i;
		}
		map.put("hrAccidentCount", hrAccidentCount);
		map.put("hrsOfDay", hrsOfDay);
		return "summary_time";
	}
}
