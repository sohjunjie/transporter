package com.transporter.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.transporter.model.AccidentCause;
import com.transporter.model.AccidentReport;
import com.transporter.service.SummaryReportService;

@Service
public class SummaryReportServiceImpl implements SummaryReportService {
	
	/*@Autowired
	private SessionFactory session;
	
	@SuppressWarnings("unchecked")
	private List<AccidentReport> allReports = session.getCurrentSession().createQuery("from AccidentReport "
			+ "where accidentDateTime>=startDate "
			+ "and accidentDateTime=<endDate").list();
	*/
	@Transactional
	public int[] summariseByCause (List<AccidentReport> allReports, List<AccidentCause> allCauses) {		
		/*HashMap<AccidentCause, Integer> causeCount = new HashMap<AccidentCause, Integer>();
		for (int i = 0; i < allCauses.size(); i++) {
			causeCount.put(allCauses.get(i), 0);
		}
		for (int i = 0; i < allReports.size(); i++) {
			AccidentCause accidentCause = allReports.get(i).getOfficialCause();
			causeCount.put(accidentCause, causeCount.get(accidentCause)+1);
		}
		return causeCount;*/
		
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
		/*HashMap<Integer, Integer> hourAccidentCount = new HashMap<Integer, Integer>();
		for (int i = 0; i < 24; i++) {
			hourAccidentCount.put(i, 0);
		}
		for (int i = 0; i < allReports.size();i++) {
			Calendar accidentDateTime = GregorianCalendar.getInstance();
			accidentDateTime.setTime(allReports.get(i).getAccidentDateTime());
			int accidentHr = accidentDateTime.get(Calendar.HOUR_OF_DAY);
			hourAccidentCount.put(accidentHr, hourAccidentCount.get(accidentHr)+1);
		}
		return hourAccidentCount;*/
		
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
