package com.transporter.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.transporter.dao.AccidentReportDao;
import com.transporter.model.AccidentReport;

@Repository
public class AccidentReportDaoImpl implements AccidentReportDao {

	@Autowired
	private SessionFactory session;
	
	@Override
	public void add(AccidentReport accidentReport) {
		session.getCurrentSession().save(accidentReport);
	}

	@Override
	public void edit(AccidentReport accidentReport) {
		session.getCurrentSession().update(accidentReport);
	}

	@Override
	public AccidentReport getAccidentReport(int reportId) {
		return (AccidentReport)session.getCurrentSession().get(AccidentReport.class, reportId);
	}

	@Override
	public void delete(int reportId) {
		session.getCurrentSession().delete(getAccidentReport(reportId));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AccidentReport> getAllAccidentReport() {
		return session.getCurrentSession().createQuery("from AccidentReport").list();
	}

}
