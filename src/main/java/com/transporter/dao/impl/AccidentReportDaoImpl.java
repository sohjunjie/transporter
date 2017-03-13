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
	
	//add a new accident report into the database
	@Override
	public void add(AccidentReport accidentReport) {
		session.getCurrentSession().save(accidentReport);
	}

	//edit an existing accident report in the database
	@Override
	public void edit(AccidentReport accidentReport) {
		session.getCurrentSession().update(accidentReport);
	}

	//retrieve an existing accident report from the database
	@Override
	public AccidentReport getAccidentReport(int reportId) {
		return (AccidentReport)session.getCurrentSession().get(AccidentReport.class, reportId);
	}

	//delete an existing accident report from the database
	@Override
	public void delete(int reportId) {
		session.getCurrentSession().delete(getAccidentReport(reportId));
	}

	//retrieve a list of all accident reports from the database
	@SuppressWarnings("unchecked")
	@Override
	public List<AccidentReport> getAllAccidentReport() {
		return session.getCurrentSession().createQuery("FROM AccidentReport").list();
	}

	//retrieve the number of pending accident report from the database
	@Override
	public Long getPendingAccidentCount() {
		return (Long) session.getCurrentSession().createQuery("select count(*) from AccidentReport "
				+ "where approvedBy=null "
				+ "and resolvedBy=null").uniqueResult();
	}

	//retrieve the list of pending accident report from  the database
	@SuppressWarnings("unchecked")
	@Override
	public List<AccidentReport> getPendingAccidentReport() {
		return session.getCurrentSession().createQuery("from AccidentReport "
				+ "where approvedBy=null "
				+ "and resolvedBy=null").list();
	}

	//retrieve the number of approved accident reports, which are yet to be resolved, from the database
	@Override
	public Long getApprovedAccidentCount() {
		return (Long) session.getCurrentSession().createQuery("select count(*) from AccidentReport "
				+ "where approvedBy!=null "
				+ "and resolvedBy=null").uniqueResult();
	}

	//retrieve the list of approved accident reports, which are yet to be resolved, from the database
	@SuppressWarnings("unchecked")
	@Override
	public List<AccidentReport> getApprovedAccidentReport() {
		return session.getCurrentSession().createQuery("from AccidentReport "
				+ "where approvedBy!=null "
				+ "and resolvedBy=null").list();
	}
	
	//retrieve the list of all approved accident reports, regardless of whether they are resolved or not, from the database
	@SuppressWarnings("unchecked")
	@Override
	public List<AccidentReport> getApprovedAndResolvedAccidentReport() {
		return session.getCurrentSession().createQuery("from AccidentReport "
				+ "where approvedBy!=null ").list();
	}
	
	//retrieve the list of resolved accident reports from the database
	@SuppressWarnings("unchecked")
	@Override
	public List<AccidentReport> getResolvedAccidentReport() {
		return session.getCurrentSession().createQuery("from AccidentReport "
				+ "where approvedBy!=null "
				+ "and resolvedBy!=null").list();
	}

	//retrieve the list of all approved accident reports, regardless of whether they are resolved or not, from the database
	//based on the range of date selected
	@SuppressWarnings("unchecked")
	@Override
	public List<AccidentReport> getApprovedAndResolvedAccidentReport(Date startDate, Date endDate) {
		return session.getCurrentSession().createQuery("from AccidentReport "
			+ "where accidentDateTime >= :startDate "
			+ "and accidentDateTime <= :endDate "
			+ "and approvedBy!=null")
				.setParameter("startDate", startDate)
				.setParameter("endDate", endDate).list();
	}
	
	//retrieve the list of approved accident reports, which are yet to be resolved, from the database
	//based on the range of date selected
	@SuppressWarnings("unchecked")
	@Override
	public List<AccidentReport> getApprovedAccidentReport(Date startDate, Date endDate) {
		return session.getCurrentSession().createQuery("from AccidentReport "
			+ "where accidentDateTime >= :startDate "
			+ "and accidentDateTime <= :endDate "
			+ "and approvedBy!=null "
			+ "and resolvedBy=null")
				.setParameter("startDate", startDate)
				.setParameter("endDate", endDate).list();
	}
	
	//retrieve the list of resolved accident reports from the database
	//based on the range of date selected
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
