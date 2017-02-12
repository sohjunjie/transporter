package com.transporter.dao;

import java.util.List;

import com.transporter.model.AccidentCause;

public interface AccidentCauseDao {

	public AccidentCause getAccidentCause(int causeId);
	public List<AccidentCause> getAllAccidentCauses();
	
}
