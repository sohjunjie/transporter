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
	private Date accidentDateTime;
	@Column
	private int numOfCasualties;
	@Column
	private String description;
	@Column
	private double longitude;
	@Column
	private double latitude;
	@Column
	private String imageLink;
	
	@Column
	private Date approvedDateTime;
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="approvedBy")
	private LTAPersonnel approvedBy;
	
	@Column
	private Date resolvedDateTime;
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="officialCause")
	private AccidentCause officialCause;
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="resolvedBy")
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

	public int getReportId() {
		return reportId;
	}
	public void setReportId(int reportId) {
		this.reportId = reportId;
	}
	public Date getReportedDateTime() {
		return accidentDateTime;
	}
	public void setReportedDateTime(Date reportedDateTime) {
		this.accidentDateTime = reportedDateTime;
	}
	public int getNumOfCasualties() {
		return numOfCasualties;
	}
	public void setNumOfCasualties(int numOfCasualties) {
		this.numOfCasualties = numOfCasualties;
	}
	public String getDescription() {
		return this.description;
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
	public String getImageLink() {
		return imageLink;
	}
	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
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
	public AccidentCause getOfficialCause() {
		return officialCause;
	}
	public void setOfficialCause(AccidentCause officialCause) {
		this.officialCause = officialCause;
	}
	public LTAPersonnel getResolvedBy() {
		return resolvedBy;
	}
	public void setResolvedBy(LTAPersonnel resolvedBy) {
		this.resolvedBy = resolvedBy;
	}
	
}
