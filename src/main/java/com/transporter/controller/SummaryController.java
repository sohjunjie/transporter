package com.transporter.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.transporter.model.AccidentCause;
import com.transporter.model.AccidentReport;
import com.transporter.service.AccidentCauseService;
import com.transporter.service.AccidentReportService;
import com.transporter.service.SummaryReportService;


@Controller
@RequestMapping("/summary")
public class SummaryController
{
	@Autowired
	private AccidentReportService accidentReportService;
	@Autowired
	private AccidentCauseService accidentCauseService;
	@Autowired
	private SummaryReportService summaryReportService;
	
	@RequestMapping(value="/cause", method=RequestMethod.GET)
	public String goSummaryCause(@RequestParam(value="startdate", required=false) String textStartDate, 
			@RequestParam(value="enddate", required=false) String textEndDate, 
			@RequestParam(value="searchoption", required=false) String searchOption, Map<String, Object> map) {
		List<AccidentCause> accidentCauses = accidentCauseService.getAllAccidentCauses();
		List<AccidentReport> accidentReports = checkSearch(textStartDate, textEndDate, searchOption);
		//List<String> strCauses = accidentCauseService.getAllAccidentCausesStr();
		int[] causeCount = summaryReportService.summariseByCause (accidentReports, accidentCauses);
		map.put("accidentCauses", accidentCauses);
		map.put("causeCount", causeCount);
		return "summary_cause";
	   }
	
	
	@RequestMapping(value="/time", method=RequestMethod.GET)
	public String goSummaryTime(@RequestParam(value="startdate", required=false) String textStartDate, 
			@RequestParam(value="enddate", required=false) String textEndDate, Map<String, Object> map, 
			@RequestParam(value="searchoption", required=false) String searchOption, HttpSession httpSession) {
		List<AccidentReport> accidentReports = checkSearch(textStartDate, textEndDate, searchOption);
		int[] hrAccidentCount = summaryReportService.summariseByTime(accidentReports);
		int[] hrsOfDay = new int[24];
		for (int i = 0; i < 24; i++) {
			hrsOfDay[i] = i;
		}
		map.put("hrAccidentCount", hrAccidentCount);
		map.put("hrsOfDay", hrsOfDay);
		return "summary_time";
	}
	
	@RequestMapping(value="/location", method=RequestMethod.GET)
	public String goSummaryLocation(@RequestParam(value="startdate", required=false) String textStartDate, 
			@RequestParam(value="enddate", required=false) String textEndDate, Map<String, Object> map, 
			@RequestParam(value="searchoption", required=false) String searchOption, HttpSession httpSession) {
		List<AccidentReport> accidentReports = checkSearch(textStartDate, textEndDate, searchOption);
		map.put("accidentReports", accidentReports);
		return "summary_location";
	   }
	
	private List<AccidentReport> checkSearch(String textStartDate, String textEndDate, String searchOption) {
		DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date startDate;
		Date endDate;
		if (searchOption==null)
			searchOption="both";
		try {
			startDate = (Date)formatter.parse(textStartDate);
			endDate = (Date)formatter.parse(textEndDate); 
			switch(searchOption) {
			case "current": return accidentReportService.getApprovedAccidentReport(startDate, endDate);
			case "archived": return accidentReportService.getResolvedAccidentReport(startDate, endDate);
			default: return accidentReportService.getApprovedAndResolvedAccidentReport(startDate, endDate);
			}
			
		} catch (Exception e) {
			switch(searchOption) {
			case "current": return accidentReportService.getApprovedAccidentReport();
			case "archived": return accidentReportService.getResolvedAccidentReport();
			default: return accidentReportService.getApprovedAndResolvedAccidentReport();
			}
		} 
	}
}
