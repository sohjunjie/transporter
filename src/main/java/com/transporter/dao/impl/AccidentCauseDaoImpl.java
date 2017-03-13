package com.transporter.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.transporter.dao.AccidentCauseDao;
import com.transporter.model.AccidentCause;

@Repository
public class AccidentCauseDaoImpl implements AccidentCauseDao {

	@Autowired
	private SessionFactory session;
	
	//get the data of a particular accident report's accident cause from the database
	@Override
	public AccidentCause getAccidentCause(int causeId) {
		return (AccidentCause)session.getCurrentSession().get(AccidentCause.class, causeId);
	}

	//get a list of all acident report's accident causes from the database
	@SuppressWarnings("unchecked")
	@Override
	public List<AccidentCause> getAllAccidentCauses() {
		return session.getCurrentSession().createQuery("from AccidentCause").list();
	}
	
}
