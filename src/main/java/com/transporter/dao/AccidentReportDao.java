package com.transporter.dao;

import java.util.Date;
import java.util.List;

import com.transporter.model.AccidentReport;

public interface AccidentReportDao {

	public void add(AccidentReport accidentReport);
	public void edit(AccidentReport accidentReport);
	public AccidentReport getAccidentReport(int reportId);
	public void delete(int reportId);
	public List<AccidentReport> getAllAccidentReport();
	
	public Long getPendingAccidentCount();
	public List<AccidentReport> getPendingAccidentReport();

	public Long getApprovedAccidentCount();
	public List<AccidentReport> getApprovedAccidentReport();
	public List<AccidentReport> getApprovedOrResolvedAccidentReport();
	public List<AccidentReport> getResolvedAccidentReport();
	
	public List<AccidentReport> getApprovedAccidentReport(Date startDate, Date endDate);
	public List<AccidentReport> getApprovedAndResolvedAccidentReport(Date startDate, Date endDate);
	public List<AccidentReport> getResolvedAccidentReport(Date startDate, Date endDate);
}
