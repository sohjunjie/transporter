package com.transporter.dao.impl;

import java.util.Date;
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
		return session.getCurrentSession().createQuery("FROM AccidentReport").list();
	}

	@Override
	public Long getPendingAccidentCount() {
		return (Long) session.getCurrentSession().createQuery("select count(*) from AccidentReport "
				+ "where approvedBy=null "
				+ "and resolvedBy=null").uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AccidentReport> getPendingAccidentReport() {
		return session.getCurrentSession().createQuery("from AccidentReport "
				+ "where approvedBy=null "
				+ "and resolvedBy=null").list();
	}

	@Override
	public Long getApprovedAccidentCount() {
		return (Long) session.getCurrentSession().createQuery("select count(*) from AccidentReport "
				+ "where approvedBy!=null "
				+ "and resolvedBy=null").uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AccidentReport> getApprovedAccidentReport() {
		return session.getCurrentSession().createQuery("from AccidentReport "
				+ "where approvedBy!=null "
				+ "and resolvedBy=null").list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AccidentReport> getApprovedAndResolvedAccidentReport() {
		return session.getCurrentSession().createQuery("from AccidentReport "
				+ "where approvedBy!=null ").list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AccidentReport> getResolvedAccidentReport() {
		return session.getCurrentSession().createQuery("from AccidentReport "
				+ "where approvedBy!=null "
				+ "and resolvedBy!=null").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AccidentReport> getApprovedAndResolvedAccidentReport(Date startDate, Date endDate) {
		return session.getCurrentSession().createQuery("from AccidentReport "
			+ "where accidentDateTime >= :startDate "
			+ "and accidentDateTime <= :endDate"
			+ "and approvedBy!=null")
				.setParameter("startDate", startDate)
				.setParameter("endDate", endDate).list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AccidentReport> getApprovedAccidentReport(Date startDate, Date endDate) {
		return session.getCurrentSession().createQuery("from AccidentReport "
			+ "where accidentDateTime >= :startDate "
			+ "and accidentDateTime <= :endDate"
			+ "and approvedBy!=null"
			+ "and resolvedBy=null")
				.setParameter("startDate", startDate)
				.setParameter("endDate", endDate).list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AccidentReport> getResolvedAccidentReport(Date startDate, Date endDate) {
		return session.getCurrentSession().createQuery("from AccidentReport "
			+ "where accidentDateTime >= :startDate "
			+ "and accidentDateTime <= :endDate"
			+ "and approvedBy!=null"
			+ "and resolvedBy!=null")
				.setParameter("startDate", startDate)
				.setParameter("endDate", endDate).list();
	}
}
