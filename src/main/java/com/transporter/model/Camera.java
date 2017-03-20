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
	//method to get the ID number of the camera
	public int getCameraId() {
		return cameraId;
	}
	//method to set the ID number of the camera	
	public void setCameraId(int cameraId) {
		this.cameraId = cameraId;
	}
	//method to get the location of the camera
	public String getFormattedAddress() {
		return formattedAddress;
	}
	//method to set the location of the camera
	public void setFormattedAddress(String formattedAddress) {
		this.formattedAddress = formattedAddress;
	}
	//method to get the longitude coordinate of the camera
	public double getLongitude() {
		return longitude;
	}
	//method to set the longitude coordinate of the camera
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	//method to get the latitude coordinate of the camera
	public double getLatitude() {
		return latitude;
	}
	//method to set the latitude coordinate of the camera
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	//method to get the type of camera (speed or traffic camera)
	public CameraType getType() {
		return type;
	}
	//method to set the type of camera (speed or traffic camera)
	public void setType(CameraType type) {
		this.type = type;
	}
	//method to get the status of camera (pending or installed)
	public CameraStatus getStatus() {
		return status;
	}
	//method to set the status of camera ((pending or installed)
	public void setStatus(CameraStatus status) {
		this.status = status;
	}
	

	
}
