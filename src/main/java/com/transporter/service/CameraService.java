package com.transporter.service;

import java.util.List;

import com.transporter.model.Camera;
import com.transporter.model.Camera.CameraStatus;
import com.transporter.model.Camera.CameraType;

public interface CameraService {
	
	public void add(int cameraId, double longitude, double latitude, CameraType type, CameraStatus status);
	public void edit(Camera camera);
	public Camera getCamera(int cameraId);
	public void delete(int cameraId);
	
	public List<Camera> getAllCamera();
	public List<Camera> getAllSpeedCamera();
	public List<Camera> getAllTrafficCamera();
	public List<Camera> getAllPendingCamera();
	public List<Camera> getAllInstalledCamera();
	
	public Long getCameraCount();
	public Long getSpeedCameraCount();
	public Long getTrafficCameraCount();
	public Long getPendingCameraCount();
	public Long getInstalledCameraCount();
	
}
