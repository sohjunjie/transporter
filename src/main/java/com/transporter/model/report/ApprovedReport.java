package com.transporter.model.report;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.transporter.model.user.LTAPersonnel;

@Entity
@Table(name="approved_report")
@PrimaryKeyJoinColumn(name="id")
@Inheritance(strategy=InheritanceType.JOINED)
public class ApprovedReport extends AccidentReport {

	@Column
	private Date approvedDateTime;

	@OneToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="id")
	private LTAPersonnel approvedBy;

	public ApprovedReport(){ }

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


}
