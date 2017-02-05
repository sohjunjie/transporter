package com.transporter.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.transporter.model.user.LTAPersonnel;

@Entity
@Table(name="accident_report")
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

	@Column
	private Date approvedDateTime;
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(referencedColumnName="id")
	private LTAPersonnel approvedBy;
	
	@Column
	private Date resolvedDateTime;
	@Column
	private int officialCause;
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(referencedColumnName="id")
	private LTAPersonnel resolvedBy;
	
	public boolean isPending(){
		return !(isApproved() || isResolved());
	}
	public boolean isApproved(){
		return (this.approvedBy != null);
	}
	public boolean isResolved(){
		return (this.resolvedBy != null);
	}
	
	public AccidentReport(){ }
	public AccidentReport(int reportId, Date reportedDateTime, int numOfCasualties, String description,
			double longitude, double latitude, Date approvedDateTime, LTAPersonnel approvedBy, Date resolvedDateTime,
			int officialCause, LTAPersonnel resolvedBy) {
		super();
		this.reportId = reportId;
		this.reportedDateTime = reportedDateTime;
		this.numOfCasualties = numOfCasualties;
		this.description = description;
		this.longitude = longitude;
		this.latitude = latitude;
		this.approvedDateTime = approvedDateTime;
		this.approvedBy = approvedBy;
		this.resolvedDateTime = resolvedDateTime;
		this.officialCause = officialCause;
		this.resolvedBy = resolvedBy;
	}
	public int getReportId() {
		return reportId;
	}
	public void setReportId(int reportId) {
		this.reportId = reportId;
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
	public Date getApprovedDateTime() {
		return approvedDateTime;
	}
	public void setApprovedDateTime(Date approvedDateTime) {
		this.approvedDateTime = approvedDateTime;
	}
	public LTAPersonnel getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(LTAPersonnel approvedBy) {
		this.approvedBy = approvedBy;
	}
	public Date getResolvedDateTime() {
		return resolvedDateTime;
	}
	public void setResolvedDateTime(Date resolvedDateTime) {
		this.resolvedDateTime = resolvedDateTime;
	}
	public int getOfficialCause() {
		return officialCause;
	}
	public void setOfficialCause(int officialCause) {
		this.officialCause = officialCause;
	}
	public LTAPersonnel getResolvedBy() {
		return resolvedBy;
	}
	public void setResolvedBy(LTAPersonnel resolvedBy) {
		this.resolvedBy = resolvedBy;
	}


	
}
