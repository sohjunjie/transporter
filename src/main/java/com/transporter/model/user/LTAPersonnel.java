package com.transporter.model.user;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="lta_personnel")
@PrimaryKeyJoinColumn(name="id")
public class LTAPersonnel extends AuthenticatedUser {

	public void approveReport(){ }
	public void resolveReport(){ }
	public void rejectReport(){ }

}
