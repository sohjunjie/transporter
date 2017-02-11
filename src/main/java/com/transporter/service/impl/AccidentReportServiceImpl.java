package com.transporter.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.transporter.dao.AccidentReportDao;
import com.transporter.model.AccidentReport;
import com.transporter.service.AccidentReportService;

@Service
public class AccidentReportServiceImpl implements AccidentReportService {

	@Autowired
	private AccidentReportDao accidentReportDao;
	
	@Transactional
	public void add(double lat, double lng, Date accidentDateTime, String accidentCause, MultipartFile accidentImage) {
		AccidentReport accidentReport = new AccidentReport();
		accidentReport.setLatitude(lat);
		accidentReport.setLongitude(lng);
		accidentReport.setReportedDateTime(accidentDateTime);
		accidentReport.setDescription(accidentCause);
		accidentReportDao.add(accidentReport);
	}

	@Transactional
	public void edit(AccidentReport accidentReport) {
		accidentReportDao.edit(accidentReport);
	}

	@Transactional
	public AccidentReport getAccidentReport(int reportId) {
		return accidentReportDao.getAccidentReport(reportId);
	}

	@Transactional
	public void delete(int reportId) {
		accidentReportDao.delete(reportId);
	}

	@Transactional
	public List<AccidentReport> getAllAccidentReport() {
		return accidentReportDao.getAllAccidentReport();
	}

}
