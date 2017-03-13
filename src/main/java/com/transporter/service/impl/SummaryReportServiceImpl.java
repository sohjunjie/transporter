package com.transporter.service.impl;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.transporter.model.AccidentCause;
import com.transporter.model.AccidentReport;
import com.transporter.service.SummaryReportService;

@Service
public class SummaryReportServiceImpl implements SummaryReportService {
	
	//returns array of number of accidents due to each cause
	@Transactional
	public int[] summariseByCause (List<AccidentReport> allReports, List<AccidentCause> allCauses) {
		int causeCount[] = new int[allCauses.size()];
		for (int i = 0; i < causeCount.length; i++) {
			causeCount[i] = 0;
		}
		if (!allReports.isEmpty()) {
			for (int i = 0; i < allReports.size(); i++) {
				causeCount[allReports.get(i).getOfficialCause().getCauseId()-1]++;
			}
		}
		return causeCount;
	}
	
	//returns array of number of accidents at each hourly time period
	@Transactional
	public int[] summariseByTime (List<AccidentReport> allReports) {
		int hourAccidentCount[] = new int[24];
		for (int i = 0; i < 24; i++) {
			hourAccidentCount[i]=0;
		}
		for (int i = 0; i < allReports.size();i++) {
			Calendar accidentDateTime = GregorianCalendar.getInstance();
			accidentDateTime.setTime(allReports.get(i).getAccidentDateTime());
			int accidentHr = accidentDateTime.get(Calendar.HOUR_OF_DAY);
			hourAccidentCount[accidentHr]++;
		}
		return hourAccidentCount;
	}
}
