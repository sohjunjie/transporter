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
	
	public List<AccidentReport> getAccidentReportBetweenDate(Date startDate, Date endDate);
	List<AccidentReport> getApprovedAndResolvedAccidentReport();
}
