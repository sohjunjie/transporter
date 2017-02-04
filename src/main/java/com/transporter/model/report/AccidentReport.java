package com.transporter.model.report;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="accident_report")
@Inheritance(strategy=InheritanceType.JOINED)
public class AccidentReport {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int reportId;
	@Column
	private Date reportedDateTime;
	@Column
	private int numOfCasualties;
	@Column
	private String description;
	@Column
	private double longitude;
	@Column
	private double latitude;

	public AccidentReport(){ }
	public AccidentReport(int reportId, Date reportedDateTime, int numOfCasualties, String description,
			double longitude, double latitude) {
		super();
		this.reportId = reportId;
		this.reportedDateTime = reportedDateTime;
		this.numOfCasualties = numOfCasualties;
		this.description = description;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public void setReportId(int reportId) {
		this.reportId = reportId;
	}
	public int getReportId() {
		return reportId;
	}
	public Date getReportedDateTime() {
		return reportedDateTime;
	}
	public void setReportedDateTime(Date reportedDateTime) {
		this.reportedDateTime = reportedDateTime;
	}
	public int getNumOfCasualties() {
		return numOfCasualties;
	}
	public void setNumOfCasualties(int numOfCasualties) {
		this.numOfCasualties = numOfCasualties;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
}
