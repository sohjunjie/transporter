package com.transporter.service;

import java.util.List;

import com.transporter.model.AccidentCause;

public interface AccidentCauseService {

	public AccidentCause getAccidentCause(int causeId);
	public List<AccidentCause> getAllAccidentCauses();
	public List<String> getAllAccidentCausesStr();
}
