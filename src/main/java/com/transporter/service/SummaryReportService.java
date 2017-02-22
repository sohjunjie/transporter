package com.transporter.service;

import java.util.Date;
import java.util.HashMap;

import com.transporter.model.AccidentCause;

public interface SummaryReportService {
	public HashMap<AccidentCause, Integer> summariseByCause (Date startDate, Date endDate);
	//public HashMap<Location, Integer> summariseByLocation (Date startDate, Date endDate);
}
