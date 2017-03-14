package com.transporter.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.transporter.dao.AccidentCauseDao;
import com.transporter.model.AccidentCause;
import com.transporter.service.AccidentCauseService;

@Service
public class AccidentCauseServiceImpl implements AccidentCauseService {

	@Autowired
	private AccidentCauseDao accidentCauseDao;
	// get the cause of an accident with specific ID	
	@Transactional
	public AccidentCause getAccidentCause(int causeId) {
		return accidentCauseDao.getAccidentCause(causeId);
	}
	// get the list of all accident causes
	@Transactional
	public List<AccidentCause> getAllAccidentCauses() {
		return accidentCauseDao.getAllAccidentCauses();
	}

}
