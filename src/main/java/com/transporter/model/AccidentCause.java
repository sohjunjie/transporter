package com.transporter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="accident_cause")
public class AccidentCause {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int causeId;
	@Column
	private String cause;
	//method to get the ID number of the cause
	public int getCauseId() {
		return causeId;
	}
	//method to set the ID number for the cause
	public void setCauseId(int causeId) {
		this.causeId = causeId;
	}
	//method to get the cause of accident
	public String getCause() {
		return cause;
	}
	//method to set the cause of accident
	public void setCause(String cause) {
		this.cause = cause;
	}	
	
}
