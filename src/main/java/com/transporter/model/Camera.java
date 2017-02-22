package com.transporter.model;

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
	private String formattedAddress;
	@Column
	private double longitude;
	@Column
	private double latitude;
	@Column
	@Enumerated(EnumType.ORDINAL)
	private CameraType type;
	@Column
	@Enumerated(EnumType.ORDINAL)
	private CameraStatus status;

	public Camera(){};
	public Camera(int cameraId, double longitude, double latitude, CameraType type, CameraStatus status){ 
		this.cameraId = cameraId;
		this.longitude = longitude;
		this.latitude = latitude;
		this.type = type;
		this.status = status;
	}

	public int getCameraId() {
		return cameraId;
	}

	public void setCameraId(int cameraId) {
		this.cameraId = cameraId;
	}

	public String getFormattedAddress() {
		return formattedAddress;
	}

	public void setFormattedAddress(String formattedAddress) {
		this.formattedAddress = formattedAddress;
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

	public CameraType getType() {
		return type;
	}

	public void setType(CameraType type) {
		this.type = type;
	}

	public CameraStatus getStatus() {
		return status;
	}

	public void setStatus(CameraStatus status) {
		this.status = status;
	}
	

	
}
