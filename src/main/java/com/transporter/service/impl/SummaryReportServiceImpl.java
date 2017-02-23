package com.transporter.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.transporter.model.AccidentCause;
import com.transporter.model.AccidentReport;
import com.transporter.service.SummaryReportService;

@Service
public class SummaryReportServiceImpl implements SummaryReportService {
	
	@Transactional
	public int[] summariseByCause (List<AccidentReport> allReports, List<AccidentCause> allCauses) {
		int causeCount[] = new int[allCauses.size()];
		for (int i = 0; i < causeCount.length; i++) {
			causeCount[i] = 0;
		}
		for (int i = 0; i < allReports.size(); i++) {
			for (int j = 0; j < causeCount.length; j++) {
				if (allReports.get(i).getOfficialCause() == allCauses.get(j)) {
					causeCount[j]++;
					break;
				}
			}
		}
		return causeCount;
	}
	
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
