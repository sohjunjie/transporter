package com.transporter.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;
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
	
	@RequestMapping(value = "/timepiechart.png", method = RequestMethod.GET)
	public void drawTimePieChart(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam(value="startdate", required=false) String textStartDate, 
			@RequestParam(value="enddate", required=false) String textEndDate,
			@RequestParam(value="searchoption", required=false) String searchOption) {
		response.setContentType("image/png");
		
		PieDataset pdSet = createTimeDataSet(textStartDate, textEndDate, searchOption);

		JFreeChart chart = createTimeChart(pdSet, "Accidents by Time");

		try {
			ChartUtilities.writeChartAsPNG(response.getOutputStream(), chart,
					750, 400);
			response.getOutputStream().close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private PieDataset createTimeDataSet(String textStartDate, String textEndDate, String searchOption) {
		DefaultPieDataset dpd = new DefaultPieDataset();
		
		List<AccidentReport> accidentReports = checkSearch(textStartDate, textEndDate, searchOption);
		
		int[] hrAccidentCount = summaryReportService.summariseByTime(accidentReports);
		
		for (int i = 0; i < 24; i++) {
			dpd.setValue(i + ":00 to " + i + ":59", hrAccidentCount[i]);
		}
		return dpd;
	}

	private JFreeChart createTimeChart(PieDataset pdSet, String chartTitle) {

		JFreeChart chart = ChartFactory.createPieChart3D(chartTitle, pdSet,
				true, true, false);
		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		plot.setStartAngle(290);
		plot.setDirection(Rotation.CLOCKWISE);
		plot.setForegroundAlpha(0.5f);
		return chart;
	}
	
	@RequestMapping(value = "/causepiechart.png", method = RequestMethod.GET)
	public void drawCausePieChart(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam(value="startdate", required=false) String textStartDate, 
			@RequestParam(value="enddate", required=false) String textEndDate,
			@RequestParam(value="searchoption", required=false) String searchOption) {
		response.setContentType("image/png");
		
		
		PieDataset pdSet = createCauseDataSet(textStartDate, textEndDate, searchOption);

		JFreeChart chart = createCauseChart(pdSet, "Accidents by Cause");

		try {
			ChartUtilities.writeChartAsPNG(response.getOutputStream(), chart,
					750, 400);
			response.getOutputStream().close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private PieDataset createCauseDataSet(String textStartDate, String textEndDate, String searchOption) {
		DefaultPieDataset dpd = new DefaultPieDataset();
		List<AccidentCause> accidentCauses = accidentCauseService.getAllAccidentCauses();
		List<AccidentReport> accidentReports = checkSearch(textStartDate, textEndDate, searchOption);
		
		int[] causeCount = summaryReportService.summariseByCause (accidentReports, accidentCauses);
		for (int i = 0; i < causeCount.length; i++) {
			dpd.setValue(accidentCauses.get(i).getCause(), causeCount[i]);
		}
		return dpd;
	}

	private JFreeChart createCauseChart(PieDataset pdSet, String chartTitle) {

		JFreeChart chart = ChartFactory.createPieChart3D(chartTitle, pdSet, true, true, false);
		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		plot.setStartAngle(290);
		plot.setDirection(Rotation.CLOCKWISE);
		plot.setForegroundAlpha(0.5f);
		return chart;
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
