package com.transporter.dao.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

import com.transporter.dao.SummaryReportDao;
import com.transporter.model.AccidentCause;
import com.transporter.model.AccidentReport;
import com.transporter.dao.AccidentReportDao;

public class SummaryReportDaoImpl implements SummaryReportDao {
	
	@Autowired
	private SessionFactory session;
	
	@SuppressWarnings("unchecked")
	private List<AccidentReport> allReports = session.getCurrentSession().createQuery("from AccidentReport "
			+ "where accidentDateTime>=startDate "
			+ "and accidentDateTime=<endDate").list();
	
	@SuppressWarnings("unchecked")
	public HashMap<AccidentCause, Integer> summariseByCause (Date startDate, Date endDate) {
		List<AccidentCause> allCauses = session.getCurrentSession().createQuery("from AccidentCause").list();
		
		HashMap<AccidentCause, Integer> causeCount = new HashMap<AccidentCause, Integer>();
		for (int i = 0; i < allCauses.size(); i++) {
			causeCount.put(allCauses.get(i), 0);
		}
		for (int i = 0; i < allReports.size(); i++) {
			AccidentCause accidentCause = allReports.get(i).getOfficialCause();
			causeCount.put(accidentCause, causeCount.get(accidentCause)+1);
		}
		return causeCount;
	}
	
	public HashMap<Integer, Integer> summariseByTime (Date startDate, Date endDate) {
		HashMap<Integer, Integer> hourAccidentCount = new HashMap<Integer, Integer>();
		for (int i = 0; i < 23; i++) {
			hourAccidentCount.put(i, 0);
		}
		for (int i = 0; i < allReports.size();i++) {
			Calendar accidentDateTime = GregorianCalendar.getInstance();
			accidentDateTime.setTime(allReports.get(i).getAccidentDateTime());
			int accidentHr = accidentDateTime.get(Calendar.HOUR_OF_DAY);
			hourAccidentCount.put(accidentHr, hourAccidentCount.get(accidentHr)+1);
		}
		return hourAccidentCount;
	}
}
