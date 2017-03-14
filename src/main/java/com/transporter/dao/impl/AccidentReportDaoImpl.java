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
	

	//add new accident report into the database
	@Override
	public void add(AccidentReport accidentReport) {
		session.getCurrentSession().save(accidentReport);
	}

	//edit existing accident report details in the database
	@Override
	public void edit(AccidentReport accidentReport) {
		session.getCurrentSession().update(accidentReport);
	}

	//retrieve existing accident report from the database based on its ID
	@Override
	public AccidentReport getAccidentReport(int reportId) {
		return (AccidentReport)session.getCurrentSession().get(AccidentReport.class, reportId);
	}

	//delete existing accident report from the database based on its ID
	@Override
	public void delete(int reportId) {
		session.getCurrentSession().delete(getAccidentReport(reportId));
	}

	//retrieve details of all accident reports from the database
	@SuppressWarnings("unchecked")
	@Override
	public List<AccidentReport> getAllAccidentReport() {
		return session.getCurrentSession().createQuery("FROM AccidentReport").list();
	}
	
	//retrieve number of pending accident reports from the database
	@Override
	public Long getPendingAccidentCount() {
		return (Long) session.getCurrentSession().createQuery("select count(*) from AccidentReport "
				+ "where approvedBy=null "
				+ "and resolvedBy=null").uniqueResult();
	}

	//retrieve details of all pending accident reports from the database
	@SuppressWarnings("unchecked")
	@Override
	public List<AccidentReport> getPendingAccidentReport() {
		return session.getCurrentSession().createQuery("from AccidentReport "
				+ "where approvedBy=null "
				+ "and resolvedBy=null").list();
	}

	//retrieve number of approved accident reports from the database
	@Override
	public Long getApprovedAccidentCount() {
		return (Long) session.getCurrentSession().createQuery("select count(*) from AccidentReport "
				+ "where approvedBy!=null "
				+ "and resolvedBy=null").uniqueResult();
	}

	//retrieve details of all approved accident reports, that are yet to be resolved, from the database
	@SuppressWarnings("unchecked")
	@Override
	public List<AccidentReport> getApprovedAccidentReport() {
		return session.getCurrentSession().createQuery("from AccidentReport "
				+ "where approvedBy!=null "
				+ "and resolvedBy=null").list();
	}
	
	//retrieve details of all approved accident reports, regardless whether they are resolved or not, from the database
	@SuppressWarnings("unchecked")
	@Override
	public List<AccidentReport> getApprovedAndResolvedAccidentReport() {
		return session.getCurrentSession().createQuery("from AccidentReport "
				+ "where approvedBy!=null").list();
	}
	
	//retrieve details of all resolved accident reports from the database
	@SuppressWarnings("unchecked")
	@Override
	public List<AccidentReport> getResolvedAccidentReport() {
		return session.getCurrentSession().createQuery("from AccidentReport "
				+ "where approvedBy!=null "
				+ "and resolvedBy!=null").list();
	}

	//retrieve details of all approved accident reports within the specific range of time, regardless whether they are resolved or not, from the database
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

	//retrieve details of all approved accident reports within the specific range of time, that are yet to be resolved, from the database
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
	
	//retrieve details of all resolved accident reports within the specific range of time from the database
	@SuppressWarnings("unchecked")
	@Override
	public List<AccidentReport> getResolvedAccidentReport(Date startDate, Date endDate) {
		return session.getCurrentSession().createQuery("from AccidentReport "
			+ "where accidentDateTime >= :startDate "
			+ "and accidentDateTime <= :endDate "
			+ "and approvedBy!=null "
			+ "and resolvedBy!=null")
				.setParameter("startDate", startDate)
				.setParameter("endDate", endDate).list();
	}
}
