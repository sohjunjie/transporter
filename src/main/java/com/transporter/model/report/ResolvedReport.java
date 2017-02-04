package com.transporter.model.report;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.transporter.model.user.LTAPersonnel;

@Entity
@Table(name="resolved_report")
@PrimaryKeyJoinColumn(name="id")
public class ResolvedReport extends ApprovedReport {

	@Column
	private Date resolvedDateTime;
	@Column
	private int officialCause;

	@OneToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="id")
	private LTAPersonnel resolvedBy;

	public ResolvedReport(){ }

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
