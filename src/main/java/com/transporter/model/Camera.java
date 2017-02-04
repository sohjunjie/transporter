package com.transporter.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="camera")
public class Camera {

	public enum CameraType{
		SPEED, TRAFFIC
	}
	public enum CameraStatus{
		PENDING, INSTALLED
	}

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int cameraId;
	@Column
	private Date dateInstalled;
	@Column
	private double longitude;
	@Column
	private double latitude;
	@Enumerated(EnumType.ORDINAL)
	private CameraType type;
	@Enumerated(EnumType.ORDINAL)
	private CameraStatus status;
	
	public Camera(){ }
	
}
