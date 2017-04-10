package com.transporter.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
	
	//method to set services for JUnit testing
	public void setServices(AccidentReportService accidentReportService, AccidentCauseService accidentCauseService,
			SummaryReportService summaryReportService) {
		this.accidentReportService = accidentReportService;
		this.accidentCauseService = accidentCauseService;
		this.summaryReportService = summaryReportService;
	}
	
	//controller returns list of accident causes and the number of occurrence of each cause
	//no search option for unresolved accidents as unresolved accidents do not have official causes yet
	@RequestMapping(value="/cause", method=RequestMethod.GET)
	public String goSummaryCause(@RequestParam(value="startdate", required=false) @DateTimeFormat(pattern="dd/MM/yyyy HH:mm") Date startDate, 
								 @RequestParam(value="enddate", required=false) @DateTimeFormat(pattern="dd/MM/yyyy HH:mm") Date endDate,
								 Map<String, Object> map) {

		List<AccidentCause> accidentCauses = accidentCauseService.getAllAccidentCauses();
		
		List<AccidentReport> accidentReports = filterByDateTime(startDate, endDate);
		
		int indexOfOthers = findIndexOfOthers(accidentCauses);
		
		int[] causeCount = summaryReportService.summariseByCause(accidentReports, accidentCauses);
		
		int[] topCauseCountIndex = findTopThreeHighestIndexInArray(causeCount, indexOfOthers);
		int[] topCauseCount = findTopCauseCount(causeCount, topCauseCountIndex);
		
		List<AccidentCause> topAccidentCauses = findTopAccidentCauses(accidentCauses, topCauseCountIndex, indexOfOthers);
		map.put("topAccidentCauses", topAccidentCauses);
		map.put("topCauseCount", topCauseCount);
		map.put("accidentCauses", accidentCauses);
		map.put("causeCount", causeCount);
		return "summary_cause";
	}

	//controller returns hours of the day from 00h to 23h and number of accidents in each time period 
	@RequestMapping(value="/time", method=RequestMethod.GET)
	public String goSummaryTime(@RequestParam(value="startdate", required=false) @DateTimeFormat(pattern="dd/MM/yyyy HH:mm") Date startDate, 
			@RequestParam(value="enddate", required=false) @DateTimeFormat(pattern="dd/MM/yyyy HH:mm") Date endDate, Map<String, int[]> map, 
			@RequestParam(value="searchoption", required=false) String searchOption) {
		List<AccidentReport> accidentReports = filterByDateTimeAndOption(startDate, endDate, searchOption);
		int[] hrAccidentCount = summaryReportService.summariseByTime(accidentReports);
		int[] hrsOfDay = new int[24];
		for (int i = 0; i < 24; i++) {
			hrsOfDay[i] = i;
		}
		map.put("hrAccidentCount", hrAccidentCount);
		map.put("hrsOfDay", hrsOfDay);
		return "summary_time";
	}
	
	//returns the locations of all accident reports
	@RequestMapping(value="/location", method=RequestMethod.GET)
	public String goSummaryLocation(@RequestParam(value="startdate", required=false) @DateTimeFormat(pattern="dd/MM/yyyy HH:mm") Date startDate, 
			@RequestParam(value="enddate", required=false) @DateTimeFormat(pattern="dd/MM/yyyy HH:mm") Date endDate, Map<String, Object> map, 
			@RequestParam(value="searchoption", required=false) String searchOption) {
		List<AccidentReport> accidentReports = filterByDateTimeAndOption(startDate, endDate, searchOption);
		map.put("accidentReports", accidentReports);
		return "summary_location";
	}
	
	//return the overall summary page
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public String goSummaryAll(@RequestParam(value="startdate", required=false) @DateTimeFormat(pattern="dd/MM/yyyy HH:mm") Date startDate, 
			@RequestParam(value="enddate", required=false) @DateTimeFormat(pattern="dd/MM/yyyy HH:mm") Date endDate, Map<String, Object> map,
			@RequestParam(value="searchoption", required=false) String searchOption) {
		List<AccidentReport> accidentReports = filterByDateTimeAndOption(startDate,endDate,searchOption);
		List<AccidentCause> accidentCauses = accidentCauseService.getAllAccidentCauses();
		List<AccidentReport> resolvedReports = filterByDateTime(startDate,endDate);
		int[] causeCount = summaryReportService.summariseByCause(resolvedReports, accidentCauses);
		int[] hrAccidentCount = summaryReportService.summariseByTime(accidentReports);
		int[] hrsOfDay = new int[24];
		for (int i = 0; i < 24; i++) {
			hrsOfDay[i] = i;
		}
		
		int indexOfOthers = findIndexOfOthers(accidentCauses);
		
		int[] topCauseCountIndex = findTopThreeHighestIndexInArray(causeCount, indexOfOthers);
		int[] topCauseCount = findTopCauseCount(causeCount, topCauseCountIndex);
		
		List<AccidentCause> topAccidentCauses = findTopAccidentCauses(accidentCauses, topCauseCountIndex, indexOfOthers);
		map.put("topAccidentCauses", topAccidentCauses);
		map.put("topCauseCount", topCauseCount);
		map.put("accidentCauses", accidentCauses);
		map.put("causeCount", causeCount);
		map.put("hrAccidentCount", hrAccidentCount);
		map.put("hrsOfDay", hrsOfDay);
		map.put("accidentReports", accidentReports);
		return "summary_all";
	}
	
	//check the status of each parameter and return the range of dates of accident reports accordingly
	//empty start and end date will return all accident reports regardless of date
	public List<AccidentReport> filterByDateTimeAndOption(Date startDate, Date endDate, String searchOption) {
		if (searchOption == null)
			searchOption = "both";
		if (startDate == null || endDate == null || startDate.after(endDate)) {
			switch(searchOption) {
			case "current": return accidentReportService.getApprovedAccidentReport();
			case "archived": return accidentReportService.getResolvedAccidentReport();
			default: return accidentReportService.getApprovedOrResolvedAccidentReport();
			}
		}
		else {
			switch(searchOption) {
			case "current": return accidentReportService.getApprovedAccidentReport(startDate, endDate);
			case "archived": return accidentReportService.getResolvedAccidentReport(startDate, endDate);
			default: return accidentReportService.getApprovedAndResolvedAccidentReport(startDate, endDate);
			}
		} 
	}
	
	//check range of dates for summary by cause (as unresolved approved accidents do not have official cause yet)
	//if date is not parseable or null, return all resolved accidents
	public List<AccidentReport> filterByDateTime(Date startDate, Date endDate) {
		if (startDate == null || endDate == null || startDate.after(endDate)) {
			return accidentReportService.getResolvedAccidentReport();
		}
		else {
			return accidentReportService.getResolvedAccidentReport(startDate, endDate);
		}
	} 
	
	public int findIndexOfOthers(List<AccidentCause> accidentCauses) {
		for (int i = 0; i < accidentCauses.size(); i++) {
			if (accidentCauses.get(i).getCause().equals("Other Causes"))
				return i;
		}
		return -1;
	}
	
	//finds the three highest values in an array and return the indexes. do not include "other causes" in top 3
	public int[] findTopThreeHighestIndexInArray(int[] causeCount, int indexOfOthers) {
		int highest, secondHighest, thirdHighest, highestIndex, secondHighestIndex, thirdHighestIndex;
		
		int [] firstThreeOrder = sortFirstThree(causeCount);
		
		highestIndex = firstThreeOrder[0];
		secondHighestIndex = firstThreeOrder[1];
		thirdHighestIndex = firstThreeOrder[2];
		highest = causeCount[highestIndex];
		secondHighest = causeCount [secondHighestIndex];
		thirdHighest = causeCount[thirdHighestIndex];
		
		for (int i=3; i< causeCount.length; i++) {
			if (i!=indexOfOthers) {
				if (causeCount[i] > highest) {
					thirdHighest = secondHighest;
					thirdHighestIndex = secondHighestIndex;
					secondHighest = highest;
					secondHighestIndex = highestIndex;
					highest = causeCount[i];
					highestIndex = i;
				}
				else if (causeCount[i] > secondHighest) {
					thirdHighest = secondHighest;
					thirdHighestIndex = secondHighestIndex;
					secondHighest = causeCount[i];
					secondHighestIndex = i;
				}
				else if (causeCount[i] > thirdHighest) {
					thirdHighest = causeCount[i];
					thirdHighestIndex = i;
				}
			}
		}
		return new int[] {highestIndex, secondHighestIndex, thirdHighestIndex};
	}
	
	//sort first three elements of an array
	public int[] sortFirstThree(int[] causeCount) {
		int highest, secondHighest, highestIndex, secondHighestIndex, thirdHighestIndex;
		
		if (causeCount[0] >= causeCount[1]) {
			highest = causeCount[0];
			highestIndex = 0;
			secondHighest = causeCount[1];
			secondHighestIndex = 1;
		}
		else {
			highest = causeCount[1];
			highestIndex = 1;
			secondHighest = causeCount[0];
			secondHighestIndex = 0;
		}
		if (causeCount[2] > highest) {
			thirdHighestIndex = secondHighestIndex;
			secondHighest = highest;
			secondHighestIndex = highestIndex;
			highest = causeCount[2];
			highestIndex = 2;
		}
		else if (causeCount[2] > secondHighest) {
			thirdHighestIndex = secondHighestIndex;
			secondHighest = causeCount[2];
			secondHighestIndex = 2;
		}
		else {
			thirdHighestIndex = 2;
		}
		
		return new int[] {highestIndex,secondHighestIndex,thirdHighestIndex};
	}
	
	//find the 3 highest occurring accident causes and use "other causes" as fourth member 
	private List<AccidentCause> findTopAccidentCauses(List <AccidentCause> accidentCauses, int[] topCauseCountIndex, int indexOfOthers) {
		List<AccidentCause> topAccidentCauses = new ArrayList<AccidentCause>();
		topAccidentCauses.add(accidentCauses.get(topCauseCountIndex[0]));
		topAccidentCauses.add(accidentCauses.get(topCauseCountIndex[1]));
		topAccidentCauses.add(accidentCauses.get(topCauseCountIndex[2]));
		AccidentCause others;
		if (indexOfOthers != -1) {
			others = accidentCauseService.getAccidentCause(14);
		}
		else {
			others = new AccidentCause();
			others.setCause("Other Causes");
		}

		topAccidentCauses.add(others);
		return topAccidentCauses;
	}
	
	//find the counts of the top 3 occurring accident causes and sums the rest of the number of other causes
	private int[] findTopCauseCount(int[] causeCount, int[] topCauseCountIndex) {
		int[] topCauseCount = new int[4];
		int sumOfOthers = 0;
		
		topCauseCount[0] = causeCount[topCauseCountIndex[0]];
		topCauseCount[1] = causeCount[topCauseCountIndex[1]];
		topCauseCount[2] = causeCount[topCauseCountIndex[2]];
		for (int i = 0; i < causeCount.length; i++) {
			if (i != topCauseCountIndex[0] && i!= topCauseCountIndex[1] && i!=topCauseCountIndex[2]) {
				sumOfOthers += causeCount[i];
			}
		}
		topCauseCount[3] = sumOfOthers;
		return topCauseCount;
	}
}
