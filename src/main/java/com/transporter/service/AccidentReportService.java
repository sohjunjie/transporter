package com.transporter.service;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.transporter.model.AccidentReport;

public interface AccidentReportService {
	
	public boolean add(String accidentLocation, double lat, double lng, Date accidentDateTime, String accidentDescription, MultipartFile accidentImage);
	public void edit(AccidentReport accidentReport);
	public AccidentReport getAccidentReport(int reportId);
	public void delete(int reportId);
	public List<AccidentReport> getAllAccidentReport();
	
	public Long getPendingAccidentCount();
	public List<AccidentReport> getPendingAccidentReport();

}
