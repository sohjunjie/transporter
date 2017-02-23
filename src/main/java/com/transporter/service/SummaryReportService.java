package com.transporter.service;

import java.util.List;

import com.transporter.model.AccidentCause;
import com.transporter.model.AccidentReport;

public interface SummaryReportService {
	public int[] summariseByCause (List<AccidentReport> allReports, List<AccidentCause> allCauses);
	public int[] summariseByTime (List<AccidentReport> allReports);
	//public HashMap<Location, Integer> summariseByLocation (Date startDate, Date endDate);
}
