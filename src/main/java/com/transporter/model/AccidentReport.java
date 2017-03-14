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
	private String formattedAddress;
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
//check if a pending report is approced ro resolved
	public boolean isPending(){
		return !(isApproved() || isResolved());
	}
//check if a report is approved
	public boolean isApproved(){
		return (this.approvedBy != null);
	}
//check if a report is resolved 
	public boolean isResolved(){
		return (this.resolvedBy != null);
	}

	public AccidentReport(){ }
//method to get the ID number of the accident report
	public int getReportId() {
		return reportId;
	}
//method to set the ID number of the accident report
	public void setReportId(int reportId) {
		this.reportId = reportId;
	}
//method to get the location of the accident 
	public String getFormattedAddress() {
		return formattedAddress;
	}
//method to set the location of the accident
	public void setFormattedAddress(String formattedAddress) {
		this.formattedAddress = formattedAddress;
	}
//method to get the time of the accident 
	public Date getAccidentDateTime() {
		return accidentDateTime;
	}
//method to set the time of the accident 
	public void setAccidentDateTime(Date reportedDateTime) {
		this.accidentDateTime = reportedDateTime;
	}
//method to get the number of casualties in the accident 
	public int getNumOfCasualties() {
		return numOfCasualties;
	}
//method to set the number of casualties in the accident 
	public void setNumOfCasualties(int numOfCasualties) {
		this.numOfCasualties = numOfCasualties;
	}
//method to get the description of the accident 
	public String getDescription() {
		return this.description;
	}
//method to set the description of the accident
	public void setDescription(String description) {
		this.description = description;
	}
//method to get the longitude coordinate of the accident's location
	public double getLongitude() {
		return longitude;
	}
//method to set the longitude coordinate of the accident's location
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
//method to get the latitude coordinate of the accident's location
	public double getLatitude() {
		return latitude;
	}
//method to set the longitude coordinate of the accident's location
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
//method to get the link of the image of accident
	public String getImageLink() {
		return imageLink;
	}
//method to set the link of the image of accident
	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}
//method to get the date that the accident is approved
	public Date getApprovedDateTime() {
		return approvedDateTime;
	}
//method to set the date that the accident is approved
	public void setApprovedDateTime(Date approvedDateTime) {
		this.approvedDateTime = approvedDateTime;
	}
//method to get the details of the LTA personnel who approves the accident report
	public LTAPersonnel getApprovedBy() {
		return approvedBy;
	}
//method to set the details of the LTA personnel who approves the accident report 
	public void setApprovedBy(LTAPersonnel approvedBy) {
		this.approvedBy = approvedBy;
	}
//method to get the date that the accident is resolved
	public Date getResolvedDateTime() {
		return resolvedDateTime;
	}
//method to set the date that the accident is resolved
	public void setResolvedDateTime(Date resolvedDateTime) {
		this.resolvedDateTime = resolvedDateTime;
	}
//method to get the official cause of accident
	public AccidentCause getOfficialCause() {
		return officialCause;
	}
//method to set the official cause of accident
	public void setOfficialCause(AccidentCause officialCause) {
		this.officialCause = officialCause;
	}
//method to get the details of the LTA personnel who resolves the 
	public LTAPersonnel getResolvedBy() {
		return resolvedBy;
	}
	public void setResolvedBy(LTAPersonnel resolvedBy) {
		this.resolvedBy = resolvedBy;
	}
	
}
