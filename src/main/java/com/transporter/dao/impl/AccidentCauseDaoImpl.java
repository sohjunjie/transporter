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
	

	//retrieve the cause of the accident from the database based on its ID
	@Override
	public AccidentCause getAccidentCause(int causeId) {
		return (AccidentCause)session.getCurrentSession().get(AccidentCause.class, causeId);
	}

	//retrieve all accident causes from the database
	@SuppressWarnings("unchecked")
	@Override
	public List<AccidentCause> getAllAccidentCauses() {
		return session.getCurrentSession().createQuery("from AccidentCause").list();
	}
	
}
