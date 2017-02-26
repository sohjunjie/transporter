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
	
	@Transactional
	public AccidentCause getAccidentCause(int causeId) {
		return accidentCauseDao.getAccidentCause(causeId);
	}

	@Transactional
	public List<AccidentCause> getAllAccidentCauses() {
		return accidentCauseDao.getAllAccidentCauses();
	}

	@Transactional
	public List<String> getAllAccidentCausesStr() {
		return accidentCauseDao.getAllAccidentCausesStr();
	}
}
