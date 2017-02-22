package com.transporter.dao;

import java.util.Date;
import java.util.List;
import java.util.HashMap;

import com.transporter.model.AccidentCause;
import com.transporter.model.AccidentReport;

public interface SummaryReportDao {
	public HashMap<AccidentCause, Integer> summariseByCause (Date startDate, Date endDate);
	public HashMap<Integer, Integer> summariseByTime (Date startDate, Date endDate);
	//public HashMap<Location, Integer> summariseByLocation (Date startDate, Date endDate);
}
