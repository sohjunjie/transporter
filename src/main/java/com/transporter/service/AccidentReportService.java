package com.transporter.service;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.transporter.model.AccidentReport;
import com.transporter.model.user.AuthenticatedUser;

public interface AccidentReportService {

	public boolean add(String accidentLocation, double lat, double lng, Date accidentDateTime, String accidentDescription, MultipartFile accidentImage);
	public void edit(AccidentReport accidentReport);
	public AccidentReport getAccidentReport(int reportId);
	public void delete(int reportId);
	public List<AccidentReport> getAllAccidentReport();

	public Long getPendingAccidentCount();
	public List<AccidentReport> getPendingAccidentReport();

	public Long getApprovedAccidentCount();
	
	public boolean approveAccidentReport(AuthenticatedUser authUser, int reportId);
	public boolean resolveAccidentReport(AuthenticatedUser authUser, int reportId, int causeId, int numOfCasualties);
	
	public List<AccidentReport> getApprovedAccidentReport();
	public List<AccidentReport> getApprovedAndResolvedAccidentReport();
	public List<AccidentReport> getResolvedAccidentReport();
	
	public List<AccidentReport> getApprovedAccidentReport(Date startDate, Date endDate);
	public List<AccidentReport> getApprovedAndResolvedAccidentReport(Date startDate, Date endDate);
	public List<AccidentReport> getResolvedAccidentReport(Date startDate, Date endDate);
	
	public List<Integer> getCountCauses();
}
