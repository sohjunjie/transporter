package com.transporter.service.impl;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.transporter.dao.SummaryReportDao;
import com.transporter.model.AccidentCause;

public class SummaryReportServiceImpl {
	
	@Autowired
	private SummaryReportDao summaryReportDao;
	
	public HashMap<AccidentCause, Integer> summariseByCause (Date startDate, Date endDate) {
		return summaryReportDao.summariseByCause(startDate, endDate);
	}
}
